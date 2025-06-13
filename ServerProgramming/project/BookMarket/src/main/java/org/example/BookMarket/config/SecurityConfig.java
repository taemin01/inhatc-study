package org.example.BookMarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //use BCrypt hash algorithm
    }

    //Redis - 램에 저장하는 건 동일 하지만 서버 메모리에 저장하여
    //운영체제가 관리하는 물리적 메모리 전체이다. 부팅부터 종료까지 유지되며 여러 프로세스 사이에 공유된다.
    //애플리케이션 메모리는 개별 프로세스가 할당받아 사용하는 가상 주소 공간(힙, 스택)을 말한다.
    //애플리케이션 메모리에 저장된 정보는 프로세스가 종료되면 모두 해제된다.
    @Bean
    protected UserDetailsService users() { //In Memory Exam
        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("Admin1234"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers("/books/add").hasRole("ADMIN")
                                .requestMatchers("/order/list").hasRole("ADMIN")
                                .anyRequest().permitAll()
                ).formLogin(
                        formLogin->formLogin.
                        loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/books/add")
                                .failureUrl("/loginfailed")
                                .usernameParameter("username")
                                .passwordParameter("password")
                )
                .logout(
                        logout->logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                );
        return http.build();
    }
}