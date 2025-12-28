package com.adityachandel.booklore.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 책 검색 및 Q&A 요청 DTO
 * 사용자가 책 내용에 대해 질문하거나 검색어를 통해 정보를 찾을 때 사용
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    
    /**
     * 검색 대상 책 ID (null일 경우 전체 검색)
     */
    private Long bookId;
    
    /**
     * 검색 쿼리 또는 질문 내용
     */
    private String query;
    
    /**
     * 검색 모드 선택
     * semantic: 의미론적 검색 (벡터 유사도 기반)
     * keyword: 키워드 검색 (전통적인 텍스트 매칭)
     * hybrid: 혼합 방식 (두 방법 모두 사용)
     */
    @Builder.Default
    private String searchMode = "hybrid";
    
    /**
     * 반환할 결과 수
     */
    @Builder.Default
    private int maxResults = 5;
    
    /**
     * 컨텍스트 윈도우 크기 (검색 시 참조할 텍스트 범위)
     */
    @Builder.Default
    private int contextWindow = 2000;
    
    /**
     * GPT-4 스타일 답변 생성 여부
     * true: 검색 결과를 기반으로 상세한 답변 생성
     * false: 관련 문서 스니펫만 반환
     */
    @Builder.Default
    private boolean generateAnswer = true;
    
    /**
     * 검색 결과에서 제외할 페이지 범위 (공백 등)
     */
    @Builder.Default
    private List<String> excludePatterns = List.of();
}
