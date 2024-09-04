package com.example.EducationClassProject.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final String[] allowUrl = {
            "/swagger-ui.html",
            "/swagger-ui/**",  // 스웨거 url 허용
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
    };

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        // http.cors(AbstractHttpConfigurer::disable); // cors 필터 비활설화하는 설정인데 하면 보안에 좋지않음
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(allowUrl).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/join/users").permitAll() // 회원가입 url 허용
                .anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
