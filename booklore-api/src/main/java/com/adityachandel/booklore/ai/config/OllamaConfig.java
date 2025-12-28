package com.adityachandel.booklore.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

/**
 * Ollama AI 서버 연결 설정 클래스
 * 
 * application.yml에서 아래와 같은 설정을 통해 Ollama 서버 정보를 구성합니다:
 * 
 * ollama:
 *   host: http://localhost:11434
 *   model: llama3.2
 *   embedding-model: nomic-embed-text
 *   timeout: 300
 *   temperature: 0.7
 *   max-tokens: 2048
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ollama")
public class OllamaConfig {
    
    /**
     * Ollama 서버 호스트 주소
     * 기본값: http://localhost:11434 (Ollama 기본 포트)
     */
    private String host = "http://localhost:11434";
    
    /**
     * 채팅 및 요약에 사용할 LLM 모델명
     * Ollama에서 지원되는 모델 중 Llama 3.2, Mistral, Gemma 등을 사용 가능
     * 예: llama3.2, mistral, gemma2, qwen2
     */
    private String model = "llama3.2";
    
    /**
     * 임베딩 생성에 사용할 모델명
     * 의미론적 검색을 위해 텍스트를 벡터로 변환할 때 사용
     * 효율적인 임베딩 모델 추천: nomic-embed-text, mxbai-embed-large
     */
    private String embeddingModel = "nomic-embed-text";
    
    /**
     * API 요청 타임아웃 (초 단위)
     * 복잡한 요약 작업의 경우 응답에 시간이 소요되므로 5분으로 설정
     */
    private int timeout = 300;
    
    /**
     * 생성 텍스트의 무작위성을 조절하는 파라미터
     * 0에 가까울수록 결정론적이고 일관된 출력
     * 1에 가까울수록 창의적이고 다양한 출력
     */
    private double temperature = 0.7;
    
    /**
     * 생성되는 최대 토큰 수
     * 긴 요약의 경우 이 값을 높여서 콘텐츠가 잘리지 않도록 설정
     */
    private int maxTokens = 2048;
}
