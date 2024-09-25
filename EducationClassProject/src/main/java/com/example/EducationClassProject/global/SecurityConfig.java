package com.example.EducationClassProject.global;

import com.example.EducationClassProject.apiPayload.exception.handler.JWTAccessDeniedHandler;
import com.example.EducationClassProject.global.filter.JWTExceptionFilter;
import com.example.EducationClassProject.global.filter.JWTFilter;
import com.example.EducationClassProject.global.filter.LoginFilter;
import com.example.EducationClassProject.global.filter.TestFilter;
import com.example.EducationClassProject.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final PrincipalDetailService principalDetailService;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] allowUrl = {
            "/swagger-ui.html",
            "/swagger-ui/**",  // 스웨거 url 허용
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
            "api/v1/classes",
            "/test",
    };

    // 로그인 필터 배치
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        // http.cors(AbstractHttpConfigurer::disable); // cors 필터 비활설화하는 설정인데 하면 보안에 좋지않음
        http.csrf(AbstractHttpConfigurer::disable); // jwt 와 session 을 stateless 로 관리하기에 disable
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(allowUrl).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/join/users", "api/v1/login").permitAll() // 회원가입, 로그인 url 허용
                .requestMatchers("/api/v1/class/**").hasRole("TEACHER") // 선생 권한을 가진 자만 클래스 생성 가능
                .anyRequest().authenticated());

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.exceptionHandling(
                (configurer -> configurer
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
        );

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter.class); // 로그인 필터 넣어주기
        http.addFilterBefore(new JWTFilter(jwtUtil, principalDetailService), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new JWTExceptionFilter(), JWTFilter.class); // jwt 에러 핸들링 필터 배치하기
        http.addFilterAfter(new TestFilter(), AnonymousAuthenticationFilter.class); // 테스트 필터 추가

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // session stateLess 로 관리





        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter(jwtUtil, principalDetailService);
    }


}
