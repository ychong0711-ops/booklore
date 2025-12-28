package com.adityachandel.booklore.ai.service;

import com.adityachandel.booklore.ai.config.OllamaConfig;
import com.adityachandel.booklore.ai.dto.SearchRequest;
import com.adityachandel.booklore.ai.dto.SearchResponse;
import com.adityachandel.booklore.model.entity.AuthorEntity;
import com.adityachandel.booklore.model.entity.BookEntity;
import com.adityachandel.booklore.model.entity.BookMetadataEntity;
import com.adityachandel.booklore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 책 검색 및 Q&A 서비스
 * 
 * 이 서비스는 RAG(Retrieval-Augmented Generation) 패턴을 구현하여:
 * 1. 의미론적 검색을 통해 관련 문서 스니펫을 찾음
 * 2. 검색된 정보를 컨텍스트로 활용하여 AI가 답변을 생성
 * 
 * 검색 모드:
 * - semantic: Ollama 임베딩을 사용한 벡터 유사도 검색
 * - keyword: 전통적인 텍스트 매칭 검색
 * - hybrid: 두 방법을 조합하여 더 나은 검색 결과 제공
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookSearchService {
    
    private final OllamaClientService ollamaClient;
    private final OllamaConfig config;
    private final BookRepository bookRepository;
    
    /**
     * 책 내용을 검색하고 질문에 답변합니다.
     */
    public SearchResponse searchAndAnswer(SearchRequest request) {
        log.info("책 검색 시작 - 모드: {}, 쿼리: {}", 
                request.getSearchMode(), truncateForLog(request.getQuery()));
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 1. 검색 대상 결정 (특정 책 또는 전체)
            List<BookEntity> targetBooks = new ArrayList<>();
            if (request.getBookId() != null && !request.getBookId().isEmpty()) {
                Optional<BookEntity> bookOpt = bookRepository.findById(request.getBookId());
                bookOpt.ifPresent(targetBooks::add);
            } else {
                targetBooks = bookRepository.findAll();
            }
            
            if (targetBooks.isEmpty()) {
                return SearchResponse.builder()
                        .errorMessage("검색할 책을 찾을 수 없습니다.")
                        .query(request.getQuery())
                        .searchMode(request.getSearchMode())
                        .build();
            }
            
            // 2. 검색 모드에 따른 검색 수행
            List<SearchResponse.SearchResult> results;
            String searchMode = request.getSearchMode() != null ? request.getSearchMode().toLowerCase() : "hybrid";
            
            switch (searchMode) {
                case "semantic" -> results = performSemanticSearch(
                        request.getQuery(), targetBooks, request.getMaxResults());
                case "keyword" -> results = performKeywordSearch(
                        request.getQuery(), targetBooks, request.getMaxResults());
                case "hybrid" -> results = performHybridSearch(
                        request.getQuery(), targetBooks, request.getMaxResults());
                default -> results = performHybridSearch(
                        request.getQuery(), targetBooks, request.getMaxResults());
            }
            
            // 3. 결과가 없으면 키워드 검색 폴백
            if (results.isEmpty()) {
                log.info("주 검색 결과 없음, 키워드 검색으로 폴백");
                results = performKeywordSearch(
                        request.getQuery(), targetBooks, request.getMaxResults());
            }
            
            long searchTime = System.currentTimeMillis() - startTime;
            
            // 4. 답변 생성 (요청된 경우)
            String answer = null;
            String modelUsed = null;
            if (request.isGenerateAnswer() && !results.isEmpty()) {
                try {
                    String context = buildContextFromResults(results, request.getContextWindow());
                    String answerResponse = generateAnswer(
                            request.getQuery(), context, request.getSearchMode());
                    
                    answer = answerResponse;
                    modelUsed = config.getModel();
                    
                    searchTime = System.currentTimeMillis() - startTime;
                    
                } catch (Exception e) {
                    log.error("답변 생성 중 오류: {}", e.getMessage());
                }
            }
            
            log.info("검색 완료 - 결과 수: {}, 소요 시간: {}ms", results.size(), searchTime);
            
            return SearchResponse.builder()
                    .answer(answer)
                    .results(results)
                    .searchTimeMs(searchTime)
                    .query(request.getQuery())
                    .searchMode(request.getSearchMode())
                    .modelUsed(modelUsed)
                    .bookId(request.getBookId())
                    .build();
            
        } catch (Exception e) {
            log.error("검색 처리 중 오류 발생: {}", e.getMessage());
            return SearchResponse.builder()
                    .errorMessage("검색 실패: " + e.getMessage())
                    .query(request.getQuery())
                    .searchMode(request.getSearchMode())
                    .build();
        }
    }
    
    /**
     * 의미론적 검색을 수행합니다.
     */
    private List<SearchResponse.SearchResult> performSemanticSearch(
            String query, List<BookEntity> books, int maxResults) {
        
        log.info("의미론적 검색 수행 중...");
        
        List<Float> queryEmbedding = ollamaClient.createEmbedding(query);
        
        List<ScoredResult> scoredResults = new ArrayList<>();
        
        for (BookEntity book : books) {
            String content = buildBookContent(book);
            if (content.isEmpty()) continue;
            
            List<String> chunks = splitIntoChunks(content, 500);
            
            for (int i = 0; i < chunks.size(); i++) {
                String chunk = chunks.get(i);
                List<Float> chunkEmbedding = ollamaClient.createEmbedding(chunk);
                
                double similarity = cosineSimilarity(queryEmbedding, chunkEmbedding);
                
                if (similarity > 0.3) {
                    scoredResults.add(new ScoredResult(
                            chunk,
                            similarity,
                            getBookTitle(book),
                            "Chunk " + (i + 1)
                    ));
                }
            }
        }
        
        return scoredResults.stream()
                .sorted(Comparator.comparingDouble(ScoredResult::score).reversed())
                .limit(maxResults)
                .map(this::toSearchResult)
                .collect(Collectors.toList());
    }
    
    /**
     * 키워드 기반 검색을 수행합니다.
     */
    private List<SearchResponse.SearchResult> performKeywordSearch(
            String query, List<BookEntity> books, int maxResults) {
        
        log.info("키워드 검색 수행 중...");
        
        List<String> keywords = extractKeywords(query);
        
        List<ScoredResult> scoredResults = new ArrayList<>();
        
        for (BookEntity book : books) {
            String content = buildBookContent(book);
            if (content.isEmpty()) continue;
            
            List<String> chunks = splitIntoChunks(content, 300);
            
            for (int i = 0; i < chunks.size(); i++) {
                String chunk = chunks.get(i);
                double score = calculateKeywordScore(chunk, keywords);
                
                if (score > 0) {
                    scoredResults.add(new ScoredResult(
                            chunk,
                            score,
                            getBookTitle(book),
                            "Chunk " + (i + 1)
                    ));
                }
            }
        }
        
        return scoredResults.stream()
                .sorted(Comparator.comparingDouble(ScoredResult::score).reversed())
                .limit(maxResults)
                .map(this::toSearchResult)
                .collect(Collectors.toList());
    }
    
    /**
     * 하이브리드 검색을 수행합니다.
     */
    private List<SearchResponse.SearchResult> performHybridSearch(
            String query, List<BookEntity> books, int maxResults) {
        
        log.info("하이브리드 검색 수행 중...");
        
        List<SearchResponse.SearchResult> semanticResults = 
                performSemanticSearch(query, books, maxResults * 2);
        
        List<SearchResponse.SearchResult> keywordResults = 
                performKeywordSearch(query, books, maxResults * 2);
        
        Map<String, ScoredResult> combinedResults = new HashMap<>();
        
        for (SearchResponse.SearchResult r : semanticResults) {
            String key = hashContent(r.getSnippet());
            combinedResults.put(key, new ScoredResult(
                    r.getSnippet(),
                    r.getScore() * 0.6,
                    r.getBookTitle(),
                    r.getSource()
            ));
        }
        
        for (SearchResponse.SearchResult r : keywordResults) {
            String key = hashContent(r.getSnippet());
            ScoredResult existing = combinedResults.get(key);
            if (existing != null) {
                existing.score(existing.score() + r.getScore() * 0.4);
            } else {
                combinedResults.put(key, new ScoredResult(
                        r.getSnippet(),
                        r.getScore() * 0.4,
                        r.getBookTitle(),
                        r.getSource()
                ));
            }
        }
        
        return combinedResults.values().stream()
                .sorted(Comparator.comparingDouble(ScoredResult::score).reversed())
                .limit(maxResults)
                .map(this::toSearchResult)
                .collect(Collectors.toList());
    }
    
    /**
     * 검색 결과를 기반으로 AI 답변을 생성합니다.
     */
    private String generateAnswer(String query, String context, String searchMode) {
        String systemPrompt = """
            당신은 책 내용에 대한 전문적인 Q&A 어시스턴트입니다.
            
            작업 지침:
            - 제공된 문서 스니펫을 기반으로 답변하세요
            - 문서에서 직접 언급된 내용만 사실로 답하세요
            - 불확실한 경우 "문서에서 확인되지 않습니다"라고 명시하세요
            - 답변은 간결하면서도 충분한 설명을 포함하세요
            - 한국어로 답변하세요
            - 답변의 출처를 언급할 수 있습니다
            """;
        
        String userPrompt = String.format("""
            다음 질문에 대해 제공된 문서를 기반으로 답변해주세요.

            [질문]
            %s

            [검색 모드]
            %s

            [참고 문서]
            %s

            답변:
            """, query, searchMode, context);
        
        List<java.util.Map<String, String>> messages = new ArrayList<>();
        messages.add(java.util.Map.of("role", "system", "content", systemPrompt));
        messages.add(java.util.Map.of("role", "user", "content", userPrompt));
        
        return ollamaClient.chat(messages);
    }
    
    /**
     * 책 전체 콘텐츠를 문자열로 구성합니다.
     */
    private String buildBookContent(BookEntity book) {
        StringBuilder content = new StringBuilder();
        
        String title = getBookTitle(book);
        if (title != null && !title.isEmpty() && !"알 수 없음".equals(title)) {
            content.append("제목: ").append(title).append("\n");
        }
        
        String author = getBookAuthor(book);
        if (author != null && !author.isEmpty() && !"알 수 없음".equals(author)) {
            content.append("저자: ").append(author).append("\n");
        }
        
        BookMetadataEntity metadata = book.getMetadata();
        if (metadata != null && metadata.getDescription() != null) {
            content.append("설명: ").append(metadata.getDescription()).append("\n");
        }
        
        return content.toString();
    }
    
    /**
     * 책 제목을 안전하게 가져옵니다.
     */
    private String getBookTitle(BookEntity book) {
        if (book.getMetadata() != null && book.getMetadata().getTitle() != null) {
            return book.getMetadata().getTitle();
        }
        return "알 수 없음";
    }
    
    /**
     * 책 저자를 안전하게 가져옵니다.
     */
    private String getBookAuthor(BookEntity book) {
        if (book.getMetadata() != null && book.getMetadata().getAuthors() != null 
                && !book.getMetadata().getAuthors().isEmpty()) {
            Set<String> authorNames = new HashSet<>();
            book.getMetadata().getAuthors().forEach(author -> {
                if (author.getName() != null) {
                    authorNames.add(author.getName());
                }
            });
            return String.join(", ", authorNames);
        }
        return "알 수 없음";
    }
    
    /**
     * 텍스트를 청크로 분할합니다.
     */
    private List<String> splitIntoChunks(String text, int chunkSize) {
        List<String> chunks = new ArrayList<>();
        String[] sentences = text.split("[.!?\\n]+");
        StringBuilder currentChunk = new StringBuilder();
        
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.isEmpty()) continue;
            
            if (currentChunk.length() + sentence.length() > chunkSize) {
                if (currentChunk.length() > 0) {
                    chunks.add(currentChunk.toString());
                }
                currentChunk = new StringBuilder(sentence);
            } else {
                if (currentChunk.length() > 0) {
                    currentChunk.append(". ");
                }
                currentChunk.append(sentence);
            }
        }
        
        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }
        
        return chunks;
    }
    
    /**
     * 쿼리에서 키워드를 추출합니다.
     */
    private List<String> extractKeywords(String query) {
        String[] words = query.toLowerCase().split("\\s+");
        Set<String> stopWords = Set.of(
                "은", "는", "이", "가", "을", "를", "의", "에", "에서", 
                "the", "is", "are", "what", "who", "where", "when", "how",
                "and", "or", "but", "in", "on", "at", "to", "for"
        );
        
        return Arrays.stream(words)
                .filter(w -> w.length() > 2 && !stopWords.contains(w))
                .collect(Collectors.toList());
    }
    
    /**
     * 키워드 점수를 계산합니다.
     */
    private double calculateKeywordScore(String content, List<String> keywords) {
        String lowerContent = content.toLowerCase();
        int matchCount = 0;
        
        for (String keyword : keywords) {
            if (lowerContent.contains(keyword.toLowerCase())) {
                matchCount++;
            }
        }
        
        return keywords.isEmpty() ? 0 : (double) matchCount / keywords.size();
    }
    
    /**
     * 두 벡터 간 코사인 유사도를 계산합니다.
     */
    private double cosineSimilarity(List<Float> v1, List<Float> v2) {
        if (v1.size() != v2.size()) return 0;
        
        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;
        
        for (int i = 0; i < v1.size(); i++) {
            dotProduct += v1.get(i) * v2.get(i);
            norm1 += v1.get(i) * v1.get(i);
            norm2 += v2.get(i) * v2.get(i);
        }
        
        if (norm1 == 0 || norm2 == 0) return 0;
        
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
    
    /**
     * 검색 결과를 컨텍스트 문자열로 구성합니다.
     */
    private String buildContextFromResults(
            List<SearchResponse.SearchResult> results, int maxLength) {
        
        StringBuilder context = new StringBuilder();
        int currentLength = 0;
        
        for (SearchResponse.SearchResult result : results) {
            String snippet = result.getSnippet();
            String entry = String.format("[%s] %s\n", 
                    result.getSource(), snippet);
            
            if (currentLength + entry.length() > maxLength) {
                break;
            }
            
            context.append(entry);
            currentLength += entry.length();
        }
        
        return context.toString();
    }
    
    /**
     * 콘텐츠 해시 생성 (중복 제거용)
     */
    private String hashContent(String content) {
        return Integer.toHexString(content.hashCode());
    }
    
    /**
     * 로그 출력을 위해 쿼리를 잘라냅니다.
     */
    private String truncateForLog(String text) {
        if (text == null) return "null";
        return text.length() > 50 ? text.substring(0, 50) + "..." : text;
    }
    
    /**
     * 검색 결과 변환
     */
    private SearchResponse.SearchResult toSearchResult(ScoredResult scored) {
        return SearchResponse.SearchResult.builder()
                .snippet(scored.content())
                .score(scored.score())
                .bookTitle(scored.bookTitle())
                .source(scored.source())
                .build();
    }
    
    /**
     * 점수가 있는 검색 결과를 위한 내부 클래스
     */
    private record ScoredResult(
            String content,
            double score,
            String bookTitle,
            String source
    ) {}
}
