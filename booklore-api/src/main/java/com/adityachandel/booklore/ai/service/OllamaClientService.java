package com.adityachandel.booklore.ai.service;

import com.adityachandel.booklore.ai.config.OllamaConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ollama REST API와 통신하는 클라이언트 서비스
 * 
 * 주요 기능:
 * - 채팅 completions API 호출 (대화, 요약, 답변 생성)
 * - 임베딩 생성 API 호출 (의미론적 검색을 위한 벡터 변환)
 * - 재시도 로직을 통한 안정성 확보
 * 
 * Ollama API仕様:
 * - 채팅: POST /api/chat
 * - 임베딩: POST /api/embed
 * - 모델 목록: GET /api/tags
 */
@Slf4j
@Service
public class OllamaClientService {
    
    private final WebClient webClient;
    private final OllamaConfig config;
    private final ObjectMapper objectMapper;
    
    /**
     * OllamaClientService 생성자
     * 
     * @param config Ollama 설정 정보
     * @param objectMapper JSON 직렬화/역직렬화를 위한 객체 매퍼
     */
    public OllamaClientService(OllamaConfig config, ObjectMapper objectMapper) {
        this.config = config;
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl(config.getHost())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
    
    /**
     * Ollama 채팅 API를 호출하여 응답을 생성합니다.
     * 
     * 이 메서드는 대규모 언어 모델과의 대화를 구현하는 핵심 기능입니다.
     * 시스템 프롬프트를 통해 AI의 역할과 행동 지침을 설정하고,
     * 사용자 메시지를 전달하여 맥락에 맞는 응답을 생성받습니다.
     * 
     * @param messages 대화에 참여할 메시지 목록
     *        각 메시지는 role(시스템/사용자/어시스턴트)과 content(내용)로 구성
     * @return AI가 생성한 응답 텍스트
     * @throws RuntimeException API 호출 실패 시 발생
     */
    public String chat(List<Map<String, String>> messages) {
        log.info("Ollama 채팅 API 호출 시작. 메시지 수: {}, 모델: {}", 
                messages.size(), config.getModel());
        
        long startTime = System.currentTimeMillis();
        
        try {
            // Ollama 채팅 API 요청 본문 구성
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", config.getModel());
            requestBody.put("messages", messages);
            requestBody.put("stream", false);  // 스트리밍 비활성화 (간단한 응답용)
            requestBody.put("options", Map.of(
                    "temperature", config.getTemperature(),
                    "num_predict", config.getMaxTokens()
            ));
            
            // API 호출 및 응답 처리
            JsonNode response = webClient.post()
                    .uri("/api/chat")
                    .bodyValue(requestBody)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse -> 
                            clientResponse.bodyToMono(String.class)
                                    .flatMap(body -> Mono.error(
                                            new RuntimeException("Ollama API 오류: " + body))))
                    .bodyToMono(JsonNode.class)
                    .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                            .filter(this::isRetryable))
                    .block(Duration.ofSeconds(config.getTimeout()));
            
            long duration = System.currentTimeMillis() - startTime;
            log.info("Ollama 채팅 API 응답 완료. 소요 시간: {}ms", duration);
            
            if (response == null || !response.has("message")) {
                throw new RuntimeException("Ollama API에서 유효하지 않은 응답을 받았습니다.");
            }
            
            return response.get("message").get("content").asText();
            
        } catch (WebClientResponseException e) {
            log.error("Ollama API 호출 중 HTTP 오류 발생: {}", e.getMessage());
            throw new RuntimeException("Ollama 서버 연결 실패: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Ollama API 호출 중 예상치 못한 오류 발생: {}", e.getMessage());
            throw new RuntimeException("AI 처리 중 오류 발생: " + e.getMessage(), e);
        }
    }
    
    /**
     * 텍스트에 대한 임베딩 벡터를 생성합니다.
     * 
     * 임베딩은 텍스트를 고차원 벡터 공간의 점으로 변환하는 과정입니다.
     * 이 벡터들은 의미적 유사도를 계산하는 데 사용되어,
     * 키워드 검색으로는 찾기 어려운 의미적으로 관련된 결과를 검색할 수 있습니다.
     * 
     * 예시 사용:
     * - "영화"와 "cinema"는 다른 단어이지만 의미적으로 유사하므로
     *   높은 유사도 점수를 가짐
     * 
     * @param text 임베딩을 생성할 텍스트
     * @return 텍스트의 임베딩 벡터 (List of floats)
     * @throws RuntimeException 임베딩 생성 실패 시 발생
     */
    public List<Float> createEmbedding(String text) {
        log.info("Ollama 임베딩 API 호출 시작. 텍스트 길이: {} 문자", text.length());
        
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", config.getEmbeddingModel());
            requestBody.put("input", text);
            
            JsonNode response = webClient.post()
                    .uri("/api/embed")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                            .filter(this::isRetryable))
                    .block(Duration.ofSeconds(config.getTimeout()));
            
            if (response == null || !response.has("embeddings")) {
                throw new RuntimeException("Ollama 임베딩 API에서 유효하지 않은 응답을 받았습니다.");
            }
            
            // JSON 배열을 List<Float>으로 변환
            List<Float> embedding = new ArrayList<>();
            JsonNode embeddings = response.get("embeddings");
            if (embeddings.isArray() && embeddings.size() > 0) {
                JsonNode firstEmbedding = embeddings.get(0);
                for (JsonNode value : firstEmbedding) {
                    embedding.add((float) value.asDouble());
                }
            }
            
            log.info("임베딩 생성 완료. 벡터 차원: {}", embedding.size());
            return embedding;
            
        } catch (Exception e) {
            log.error("임베딩 생성 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("임베딩 생성 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * Ollama에서 사용 가능한 모델 목록을 조회합니다.
     * 
     * 이 메서드는 현재 Ollama 서버에 다운로드된 모델들을 확인하는 데 사용됩니다.
     * 설정된 모델이 있는지 확인하거나, 사용 가능한 모델 목록을 보여주는 UI에 활용할 수 있습니다.
     * 
     * @return 모델 목록 (모델명, 크기, 수정일等信息 포함)
     */
    public List<Map<String, Object>> listModels() {
        log.info("Ollama 모델 목록 조회");
        
        try {
            JsonNode response = webClient.get()
                    .uri("/api/tags")
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block(Duration.ofSeconds(30));
            
            if (response == null || !response.has("models")) {
                return new ArrayList<>();
            }
            
            List<Map<String, Object>> models = new ArrayList<>();
            for (JsonNode model : response.get("models")) {
                Map<String, Object> modelInfo = new HashMap<>();
                modelInfo.put("name", model.get("name").asText());
                modelInfo.put("size", model.has("size") ? model.get("size").asLong() : 0);
                modelInfo.put("modifiedAt", model.has("modified_at") ? 
                        model.get("modified_at").asText() : "");
                models.add(modelInfo);
            }
            
            return models;
            
        } catch (Exception e) {
            log.error("모델 목록 조회 중 오류 발생: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Ollama 서버에 연결할 수 있는지 확인합니다.
     * 
     * @return 서버 연결 가능 여부
     */
    public boolean isServerAvailable() {
        try {
            List<Float> testEmbedding = createEmbedding("health check");
            return testEmbedding != null && !testEmbedding.isEmpty();
        } catch (Exception e) {
            log.warn("Ollama 서버 연결 불가: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 재시도 가능한 오류인지 판단합니다.
     * 네트워크 타임아웃이나 일시적인 서버 오류의 경우 재시도합니다.
     */
    private boolean isRetryable(Throwable throwable) {
        if (throwable instanceof WebClientResponseException) {
            int statusCode = ((WebClientResponseException) throwable).getStatusCode().value();
            // 5xx 서버 오류는 재시도 가능
            return statusCode >= 500;
        }
        // 네트워크 관련 예외는 재시도
        return throwable instanceof java.net.SocketException ||
               throwable instanceof java.net.SocketTimeoutException;
    }
}
