package org.example.week5.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

@Configuration
@ComponentScan("org.example.week5")
public class MessageConfig implements WebMvcConfigurer {
    @Bean
    public MessageSource messageSource() { //MessageSource는 메시지 파일을 로딩하고 메시지 제공하는 Bean
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("messages/messages"); //messages 디렉터리 안에 있는 messages로 시작하는 파일들을 읽어서 처리하겠다는 의미 확장자는 자동으로 붙음
        messageSource.setDefaultEncoding("UTF-8"); //인코딩 지정
        messageSource.setCacheSeconds(60); // 1분 캐시(1분마다 메시지 파일 변경 여부를 확인한다.)
        messageSource.setUseCodeAsDefaultMessage(true); // 메시지 키가 없으면 키 자체를 메시지로 사용한다.
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() { //LocaleResolver는 현재 사용자의 언어(로케일)을 결정하는 Bean
        //여기 코드 로직은 사용자의 로케일을 어디에 저장할지 정하는 설정이다
        //여기서는 세션에 저장하고 기본 언어를 한국어로 정했다.
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() { //LocaleChangeInterceptor는 요청 시 lang 파라미터로 로케일 변경을 가능하게 해주는 인터셉터
        //URL 요청에서 lang 파라미터를 통해 로케일을 변경할 수 있도록 해주는 인터셉터이다.
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang"); // URL 파라미터 이름
        return localeChangeInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //인터셉터를 스프링 MVC에 등록해야 작동한다. 이걸 해주지 않아서 작동하지 않았었음
        //동작 원리는 lang 파라미터를 감지하게 만든다.
        registry.addInterceptor(localeChangeInterceptor());
    }
}
