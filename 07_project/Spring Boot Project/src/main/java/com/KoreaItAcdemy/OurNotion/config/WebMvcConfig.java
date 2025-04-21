package com.KoreaItAcdemy.OurNotion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 정적 리소스 핸들러 설정 클래스
 * 이 클래스는 /upload/** 경로의 요청을 file:///C:/upload/ 로 매핑하여,
 * 로컬에 저장된 프로필 사진 등 외부 파일을 웹에서 접근 가능하도록 합니다.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/upload/**" 경로의 요청이 오면 "file:///C:/upload/" 디렉토리에서 리소스를 찾도록 설정
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///C:/upload/");
    }
}
