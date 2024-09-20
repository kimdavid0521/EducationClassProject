package com.example.EducationClassProject.global;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메세지 브로커 기반의 웹소켓 통신 활성화
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // client 와 server 간의 메세지 전송 경로 정의
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 브로커의 목적지 정의 ( 클라이언트가 특정 주제를 구독할 수 있도록 해줍니다. )
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 특정 목적지로 메세지를 보낼때 그 메세지가 어플리케이션으로 처리될 경로를 지정합니다.
    }

    // stomp 엔트포인트 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
    }

}
