package org.example.BookMarket.config;

import org.example.BookMarket.Interceptor.AuditingInterceptor;
import org.example.BookMarket.Interceptor.MonitoringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {
    private String addPathBooks = "books/**";
    //빈 객체 등록 과정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MonitoringInterceptor()).addPathPatterns(addPathBooks);
        registry.addInterceptor(new AuditingInterceptor()).addPathPatterns(addPathBooks);
    }
}
