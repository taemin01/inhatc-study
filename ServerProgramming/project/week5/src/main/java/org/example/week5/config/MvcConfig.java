package org.example.week5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${file.uploadDir}")
    String fileDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") //images로 시작하는 URL 요청을 처리
                .addResourceLocations("file:///" + fileDir) //fileDir에 저장된 경로를 사용
                .setCachePeriod(60 * 60 * 24 * 30); // 30일 캐시
    }
}


