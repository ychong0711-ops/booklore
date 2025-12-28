package com.adityachandel.booklore.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 책 요약 응답 DTO
 * Ollama AI를 통해 생성된 요약 결과를 반환
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummarizeResponse {
    
    /**
     * 생성된 요약 텍스트
     */
    private String summary;
    
    /**
     * 사용된 AI 모델명
     */
    private String modelUsed;
    
    /**
     * 소모된 토큰 수
     */
    private int tokensUsed;
    
    /**
     * 처리 소요 시간 (초 단위)
     */
    private long processingTimeSeconds;
    
    /**
     * 요약 스타일
     */
    private String summaryStyle;
    
    /**
     * 원본 텍스트 길이 (단어 수)
     */
    private int originalWordCount;
    
    /**
     * 요약 비율 (원본 대비 요약된 비율)
     */
    private double compressionRatio;
    
    /**
     * 처리 완료 타임스탬프
     */
    private LocalDateTime processedAt;
    
    /**
     * 오류 발생 시 오류 메시지, 정상 시 null
     */
    private String errorMessage;
}
