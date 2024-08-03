package hongik.demo_book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //클라이언트가 자격증명(쿠키, 인증헤더 등)을 포함한 요청을 허용
        config.addAllowedOriginPattern("*"); // 모든 출처의 요청을 허용
        config.addAllowedHeader("*"); //모든 요청 헤더를 허용
        config.addAllowedMethod("*"); //모든 HTTP 메소드 허용

        source.registerCorsConfiguration("/api/**", config); // /api/** 패턴에 대한 cors설정을 등록
        return new CorsFilter(source); // cors필터를 반환하여 spring security필터 체인에 추가
    }
}