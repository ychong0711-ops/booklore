package com.adityachandel.booklore.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * SPA (Single Page Application) 리다이렉트 컨트롤러
 * - Angular/React/Vue 등의 SPA에서 라우팅이 404로 표시되는 것을 방지
 * - 브라우저에서 직접深层링크를 접근할 때 index.html으로 포워딩
 */
@Controller
public class SpaRedirectController {

    /**
     * API 엔드포인트를 제외한 모든 경로를 index.html로 리다이렉트
     * 이렇게 하면 Angular 라우터가 URL을 처리할 수 있습니다.
     */
    @GetMapping(value = "/{path:^(?!api|ws|swagger|v1|v3|oauth|login).*}/**")
    public String redirect() {
        return "forward:/index.html";
    }
}
