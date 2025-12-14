package com.example.umc9th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정 활성화 -> 직접 작성한 보안 설정이 Spring Security 기본 설정보다 우선 적용
@Configuration
public class SecurityConfig {

    private final String[] allowUris = {
            "/auth/signup",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(allowUris).permitAll() // 위에 정의한 URI는 인증 없이 접근 가능
                .requestMatchers("/admin/**").hasRole("ADMIN") // "/admin/..."은 ADMIN 역할을 가진 사용자만 접근 가능
                .anyRequest().authenticated() // 그 외 요청은 인증 필요
        )
                .formLogin(form -> form // 폼 로그인 사용
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공 시 항상(alwayUse = true) Swagger로 리다이렉트
                        .permitAll() // 로그인 페이지는 모든 사용자가 접근 가능
                ).csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutUrl("/logout") // "/logout" 으로 로그아웃
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리다이텍트
                        .permitAll() // 로그아웃 페이지도 모든 사용자가 접근 가능
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
