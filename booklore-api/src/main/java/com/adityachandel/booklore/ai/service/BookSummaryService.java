package com.adityachandel.booklore.ai.service;

import com.adityachandel.booklore.ai.config.OllamaConfig;
import com.adityachandel.booklore.ai.dto.SummarizeRequest;
import com.adityachandel.booklore.ai.dto.SummarizeResponse;
import com.adityachandel.booklore.model.entity.BookEntity;
import com.adityachandel.booklore.model.entity.BookMetadataEntity;
import com.adityachandel.booklore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 책 콘텐츠 요약 서비스
 * 
 * 이 서비스는 Ollama AI를 활용하여 책의 내용을 사용자가 지정한 스타일로 요약합니다.
 * 지원되는 요약 스타일:
 * - brief: 간략한 개요 (2-3 문장)
 * - detailed: 상세한 요약 (여러 단락)
 * - bullet: 핵심 포인트 목록 형식
 * - academic: 학술적 스타일의 분석적 요약
 * - narrative: 서사 흐름을 따른 이야기 형식 요약
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookSummaryService {
    
    private final OllamaClientService ollamaClient;
    private final OllamaConfig config;
    private final BookRepository bookRepository;
    
    /**
     * 책 요약 기능을 수행합니다.
     * 
     * 이 메서드는 입력된 콘텐츠를 분석하여 지정된 스타일의 요약을 생성합니다.
     * 데이터베이스에서 책 정보를 조회하거나, 사용자가 직접 제공한 콘텐츠를 요약할 수 있습니다.
     * 
     * 처리 과정:
     * 1. 책 ID가 제공된 경우 데이터베이스에서 책 정보 조회
     * 2. 콘텐츠 길이 검증 (토큰 제한 고려)
     * 3. 스타일별 시스템 프롬프트 선택
     * 4. Ollama API 호출하여 요약 생성
     * 5. 응답 처리 및 통계 계산
     * 
     * @param request 요약 요청 정보 (책 ID, 콘텐츠, 스타일 등)
     * @return 생성된 요약 결과 및 메타데이터
     */
    public SummarizeResponse summarize(SummarizeRequest request) {
        log.info("책 요약 시작 - 스타일: {}, 범위: {}", 
                request.getSummaryStyle(), request.getScope());
        
        long startTime = System.currentTimeMillis();
        String contentToSummarize;
        int originalWordCount = 0;
        
        // 책 ID가 제공된 경우 데이터베이스에서 콘텐츠 조회
        if (request.getBookId() != null) {
            Optional<BookEntity> bookOpt = bookRepository.findById(request.getBookId());
            
            if (bookOpt.isEmpty()) {
                return SummarizeResponse.builder()
                        .errorMessage("책을 찾을 수 없습니다: " + request.getBookId())
                        .processedAt(LocalDateTime.now())
                        .build();
            }
            
            BookEntity book = bookOpt.get();
            contentToSummarize = buildContentFromBook(book, request.getScope());
            String bookTitle = getBookTitle(book);
            log.info("책 '{}'에서 콘텐츠 추출 완료. 길이: {} 문자", 
                    bookTitle, contentToSummarize.length());
        } else if (request.getContent() != null && !request.getContent().isEmpty()) {
            // 사용자가 직접 콘텐츠 제공
            contentToSummarize = request.getContent();
        } else {
            return SummarizeResponse.builder()
                    .errorMessage("요약할 콘텐츠가 없습니다. 책 ID 또는 콘텐츠를 제공해주세요.")
                    .processedAt(LocalDateTime.now())
                    .build();
        }
        
        // 콘텐츠 길이 검증 및 최적화
        contentToSummarize = optimizeContentForSummarization(
                contentToSummarize, request.getMaxWords());
        originalWordCount = contentToSummarize.split("\\s+").length;
        
        // 요약 스타일별 시스템 프롬프트 생성
        String systemPrompt = buildSummarySystemPrompt(request.getSummaryStyle());
        String userPrompt = buildSummaryUserPrompt(contentToSummarize, request);
        
        // Ollama API 호출
        try {
            List<java.util.Map<String, String>> messages = new ArrayList<>();
            messages.add(java.util.Map.of("role", "system", "content", systemPrompt));
            messages.add(java.util.Map.of("role", "user", "content", userPrompt));
            
            String summary = ollamaClient.chat(messages);
            long processingTime = (System.currentTimeMillis() - startTime) / 1000;
            
            // 요약 비율 계산
            int summaryWordCount = summary.split("\\s+").length;
            double compressionRatio = originalWordCount > 0 ? 
                    (double) summaryWordCount / originalWordCount : 0;
            
            log.info("요약 완료 - 원본: {}단어, 요약: {}단어, 압축률: {:.1%}", 
                    originalWordCount, summaryWordCount, compressionRatio);
            
            return SummarizeResponse.builder()
                    .summary(summary)
                    .modelUsed(config.getModel())
                    .tokensUsed(estimateTokenCount(summary))
                    .processingTimeSeconds(processingTime)
                    .summaryStyle(request.getSummaryStyle())
                    .originalWordCount(originalWordCount)
                    .compressionRatio(Math.round(compressionRatio * 100.0) / 100.0)
                    .processedAt(LocalDateTime.now())
                    .build();
            
        } catch (Exception e) {
            log.error("요약 처리 중 오류 발생: {}", e.getMessage());
            return SummarizeResponse.builder()
                    .errorMessage("요약 실패: " + e.getMessage())
                    .processedAt(LocalDateTime.now())
                    .build();
        }
    }
    
    /**
     * 책 객체에서 지정된 범위에 따라 콘텐츠를 구성합니다.
     * 
     * @param book 책 엔티티
     * @param scope 요약 범위 (all, chapter, description)
     * @return 구성된 콘텐츠 문자열
     */
    private String buildContentFromBook(BookEntity book, String scope) {
        StringBuilder content = new StringBuilder();
        
        String lowerScope = scope.toLowerCase();
        
        if ("description".equals(lowerScope)) {
            // 책 설명만 요약
            BookMetadataEntity metadata = book.getMetadata();
            if (metadata != null && metadata.getDescription() != null) {
                content.append("책 제목: ").append(getBookTitle(book)).append("\n");
                content.append("설명: ").append(metadata.getDescription());
            }
            
        } else if ("chapter".equals(lowerScope)) {
            // 챕터 내용 요약 (있는 경우)
            content.append("책 제목: ").append(getBookTitle(book)).append("\n");
            BookMetadataEntity metadata = book.getMetadata();
            if (metadata != null && metadata.getDescription() != null) {
                content.append("내용 개요: ").append(metadata.getDescription());
            }
            
        } else {
            // 전체 내용 요약 (all 또는 기본값)
            content.append("책 제목: ").append(getBookTitle(book)).append("\n");
            content.append("저자: ").append(getBookAuthor(book)).append("\n");
            BookMetadataEntity metadata = book.getMetadata();
            if (metadata != null && metadata.getDescription() != null) {
                content.append("내용 개요: ").append(metadata.getDescription()).append("\n");
            }
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
     * 요약 스타일별 시스템 프롬프트를 생성합니다.
     * 
     * 각 스타일은 AI에게 명확한 지침을 제공하여 일관된 형태의 출력을 생성합니다.
     */
    private String buildSummarySystemPrompt(String style) {
        String lowerStyle = style.toLowerCase();
        
        return switch (lowerStyle) {
            case "brief" -> """
                당신은 전문적인 책 평론가이자 요약가입니다. 
                간결하고 핵심적인 요약을 작성하는 것이 당신의 전문 분야입니다.
                
                작업 지침:
                - 2-3 문장으로 책을 간결하게 요약하세요
                - 핵심 플롯과 주요 주제만 포함하세요
                - 불필요한 세부사항은 생략하세요
                - 객관적이고 정보에 충실한 톤을 유지하세요
                """;
                
            case "bullet" -> """
                당신은 독서 가이드를 작성하는 전문가입니다.
                핵심 정보를 구조화된 목록 형태로 제공하는 것이 당신의 강점입니다.
                
                작업 지침:
                - 핵심 포인트를 불릿 포인트 형식으로 나열하세요
                - 각 포인트는 명확하고 간결해야 합니다
                - 포함할 내용: 주요 플롯, 핵심 테마, 독특한 요소
                - 5-8개의 핵심 포인트로 구성하세요
                """;
                
            case "academic" -> """
                당신은 학술적 분석가입니다. 텍스트를 분석적이고 비판적인 관점에서 평가합니다.
                
                작업 지침:
                - 학술적 어조로 분석적 요약을 작성하세요
                - 문학적 기법, 주제적 분석, 역사적 맥락을 포함하세요
                - 저자의 의도와 독자에게 미치는 영향을 분석하세요
                - 객관적이면서도 통찰력 있는 관점을 유지하세요
                """;
                
            case "narrative" -> """
                당신은 스토리텔러입니다. 이야기를 매력적으로 전달하는 것이 당신의 재능입니다.
                
                작업 지침:
                - 이야기 흐름을 따라가며 요약하세요
                - 독자가 몰입할 수 있는 서사적 형식을 사용하세요
                - 주요 사건과 캐릭터의 여정을 포함하세요
                - 스포일러를 최소화하면서도 충분한 정보를 제공하세요
                """;
                
            default -> """
                당신은 전문적인 책 요약가입니다. 포괄적이고 균형 잡힌 요약을 작성하는 것이 전문입니다.
                
                작업 지침:
                - 책의 전체적인 내용을 포괄적으로 요약하세요
                - 주요 플롯, 캐릭터, 테마를 균형 있게 다루세요
                - 저자의 의도와 독자에게 전달하려는 메시지를 설명하세요
                - 객관적이고 정보에 충실한 톤을 유지하세요
                - 적절한 수준의 세부사항을 포함하되 핵심에 집중하세요
                """;
        };
    }
    
    /**
     * 사용자 프롬프트를 생성합니다.
     */
    private String buildSummaryUserPrompt(String content, SummarizeRequest request) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("다음 텍스트를 ").append(request.getSummaryStyle()).append(" 스타일로 요약해주세요.\n\n");
        prompt.append("요약할 텍스트:\n");
        prompt.append(content);
        
        if (request.getMaxWords() > 0) {
            prompt.append("\n\n요약 길이 제한: 약 ").append(request.getMaxWords()).append("단어 이내");
        }
        
        return prompt.toString();
    }
    
    /**
     * 콘텐츠를 요약에 적합하게 최적화합니다.
     */
    private String optimizeContentForSummarization(String content, int maxWords) {
        final int ESTIMATED_TOKENS_PER_WORD = 4;
        int estimatedTokens = content.length() / ESTIMATED_TOKENS_PER_WORD;
        final int MAX_CONTEXT_TOKENS = 6000;
        
        if (estimatedTokens > MAX_CONTEXT_TOKENS) {
            int maxChars = MAX_CONTEXT_TOKENS * ESTIMATED_TOKENS_PER_WORD;
            log.info("콘텐츠가 너무 깁니다. {} -> {} 문자로 축소합니다.", 
                    content.length(), maxChars);
            content = content.substring(0, Math.min(maxChars, content.length()));
        }
        
        if (maxWords > 0) {
            String[] words = content.split("\\s+");
            if (words.length > maxWords) {
                content = String.join(" ", java.util.Arrays.copyOf(words, maxWords));
            }
        }
        
        return content;
    }
    
    /**
     * 토큰 수를 추정합니다.
     */
    private int estimateTokenCount(String text) {
        return text.length() / 3;
    }
}
