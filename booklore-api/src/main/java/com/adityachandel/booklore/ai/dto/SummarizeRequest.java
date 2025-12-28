package com.adityachandel.booklore.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 책 요약 요청 DTO
 * 사용자가 특정 책이나 콘텐츠에 대한 요약을 요청할 때 사용
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummarizeRequest {
    
    /**
     * 요약할 책의 고유 식별자
     * 데이터베이스에서 해당 책의 정보를 조회하는 데 사용
     */
    private Long bookId;
    
    /**
     * 요약할 텍스트 내용
     * 사용자가 직접 요약할 텍스트를 입력하는 경우 활용
     */
    private String content;
    
    /**
     * 요약 스타일 지정
     * brief: 간략한 요약 (2-3 문장)
     * detailed: 상세한 요약 (단락 형태)
     * bullet: 핵심 포인트 형식
     */
    @Builder.Default
    private String summaryStyle = "detailed";
    
    /**
     * 요약 대상 범위 지정
     * all: 전체 책 내용 요약
     * chapter: 특정 챕터 요약
     * description: 책 설명만 요약
     */
    @Builder.Default
    private String scope = "all";
    
    /**
     * 원하는 요약 길이 (단어 수 기준)
     * 0 이하일 경우 모델이 적절한 길이 결정
     */
    @Builder.Default
    private int maxWords = 500;
}
