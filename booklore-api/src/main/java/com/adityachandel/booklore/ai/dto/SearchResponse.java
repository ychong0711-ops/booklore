package com.adityachandel.booklore.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 책 검색 및 Q&A 응답 DTO
 * 검색 결과와 AI가 생성한 답변을 반환
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    
    /**
     * AI가 생성한 답변 (generateAnswer=true일 경우)
     * 질문에 대한 직접적인 답변을 포함
     */
    private String answer;
    
    /**
     * 검색된 관련 문서 스니펫 목록
     */
    private List<SearchResult> results;
    
    /**
     * 검색에 소요된 시간 (밀리초)
     */
    private long searchTimeMs;
    
    /**
     * 검색에 사용된 쿼리
     */
    private String query;
    
    /**
     * 검색 모드
     */
    private String searchMode;
    
    /**
     * 사용된 AI 모델명
     */
    private String modelUsed;
    
    /**
     * 검색된 책 ID (null일 경우 전체 검색)
     */
    private String bookId;
    
    /**
     * 오류 발생 시 오류 메시지
     */
    private String errorMessage;
    
    /**
     * 개별 검색 결과를 나타내는 내부 클래스
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchResult {
        /**
         * 관련 텍스트 스니펫
         */
        private String snippet;
        
        /**
         * 해당 스니펫의 유사도 점수 (0.0 ~ 1.0)
         */
        private double score;
        
        /**
         * 스니펫의 출처 (예: 페이지 번호, 챕터명)
         */
        private String source;
        
        /**
         * 스니펫이 포함된 책 제목
         */
        private String bookTitle;
    }
}
