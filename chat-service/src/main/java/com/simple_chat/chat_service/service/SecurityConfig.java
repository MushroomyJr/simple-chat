package com.simple_chat.chat_service.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/api/auth/register", "/api/auth/login", "/api/chat/create", "api/chat/*/edit/name").permitAll()
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }
}
