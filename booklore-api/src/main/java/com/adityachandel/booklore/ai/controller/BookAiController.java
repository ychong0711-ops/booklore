package com.adityachandel.booklore.ai.controller;

import com.adityachandel.booklore.ai.dto.SummarizeRequest;
import com.adityachandel.booklore.ai.dto.SummarizeResponse;
import com.adityachandel.booklore.ai.dto.SearchRequest;
import com.adityachandel.booklore.ai.dto.SearchResponse;
import com.adityachandel.booklore.ai.service.BookSummaryService;
import com.adityachandel.booklore.ai.service.BookSearchService;
import com.adityachandel.booklore.ai.service.OllamaClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AI 기능 REST API 컨트롤러
 * 
 * 이 컨트롤러는 BookLore 애플리케이션의 AI 기능을 외부에 노출합니다.
 * 프론트엔드 Angular 앱에서 HTTP 요청을 통해 AI 기능을 사용할 수 있습니다.
 * 
 * API 엔드포인트:
 * - POST /api/ai/summarize: 책 요약
 * - POST /api/ai/search: 책 검색 및 Q&A
 * - GET /api/ai/models: 사용 가능한 Ollama 모델 목록
 * - GET /api/ai/status: Ollama 서버 상태 확인
 */
@Slf4j
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 개발 환경용, 프로덕션에서는 특정 도메인으로 제한 권장
public class BookAiController {
    
    private final BookSummaryService summaryService;
    private final BookSearchService searchService;
    private final OllamaClientService ollamaClient;
    
    /**
     * 책 콘텐츠를 AI로 요약합니다.
     * 
     * 요청 예시:
     * POST /api/ai/summarize
     * {
     *   "bookId": "550e8400-e29b-41d4-a716-446655440000",
     *   "summaryStyle": "detailed",
     *   "scope": "all"
     * }
     * 
     * 응답 예시:
     * {
     *   "summary": "이 책은...",
     *   "modelUsed": "llama3.2",
     *   "tokensUsed": 150,
     *   "processingTimeSeconds": 5,
     *   "compressionRatio": 0.15
     * }
     * 
     * @param request 요약 요청 정보
     * @return 생성된 요약 결과
     */
    @PostMapping("/summarize")
    public ResponseEntity<SummarizeResponse> summarize(
            @Valid @RequestBody SummarizeRequest request) {
        
        log.info("요약 API 호출 - 스타일: {}, 범위: {}", 
                request.getSummaryStyle(), request.getScope());
        
        SummarizeResponse response = summaryService.summarize(request);
        
        if (response.getErrorMessage() != null) {
            log.warn("요약 실패: {}", response.getErrorMessage());
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 책 내용을 검색하고 질문에 답변합니다.
     * 
     * 요청 예시:
     * POST /api/ai/search
     * {
     *   "bookId": "550e8400-e29b-41d4-a716-446655440000",
     *   "query": "이 책의 주요 테마는 무엇인가요?",
     *   "searchMode": "hybrid",
     *   "generateAnswer": true
     * }
     * 
     * 응답 예시:
     * {
     *   "answer": "이 책의 주요 테마는...",
     *   "results": [...],
     *   "searchTimeMs": 1500,
     *   "modelUsed": "llama3.2"
     * }
     * 
     * @param request 검색 요청 정보
     * @return 검색 결과 및 AI 답변
     */
    @PostMapping("/search")
    public ResponseEntity<SearchResponse> search(
            @Valid @RequestBody SearchRequest request) {
        
        log.info("검색 API 호출 - 모드: {}, 쿼리: {}", 
                request.getSearchMode(), request.getQuery());
        
        SearchResponse response = searchService.searchAndAnswer(request);
        
        if (response.getErrorMessage() != null) {
            log.warn("검색 실패: {}", response.getErrorMessage());
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Ollama 서버에 있는 사용 가능한 모델 목록을 조회합니다.
     * 
     * GET /api/ai/models
     * 
     * 응답 예시:
     * [
     *   {"name": "llama3.2", "size": 4700000000, "modifiedAt": "2024-01-15T10:30:00Z"},
     *   {"name": "nomic-embed-text", "size": 270000000, "modifiedAt": "2024-01-10T08:00:00Z"}
     * ]
     * 
     * @return 사용 가능한 모델 목록
     */
    @GetMapping("/models")
    public ResponseEntity<List<Map<String, Object>>> listModels() {
        log.info("모델 목록 조회 API 호출");
        
        List<Map<String, Object>> models = ollamaClient.listModels();
        
        return ResponseEntity.ok(models);
    }
    
    /**
     * Ollama 서버의 연결 상태를 확인합니다.
     * 
     * GET /api/ai/status
     * 
     * 응답 예시:
     * {
     *   "available": true,
     *   "message": "Ollama 서버가 정상적으로 연결되었습니다."
     * }
     * 
     * @return 서버 상태 정보
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        log.info("서버 상태 확인 API 호출");
        
        boolean available = ollamaClient.isServerAvailable();
        
        Map<String, Object> status = Map.of(
                "available", available,
                "message", available ? 
                        "Ollama 서버가 정상적으로 연결되었습니다." : 
                        "Ollama 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인해주세요."
        );
        
        return ResponseEntity.ok(status);
    }
}
