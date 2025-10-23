package com.wetech.backend_spring_wetech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint để frontend kết nối
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // prefix gửi từ backend -> frontend
        config.enableSimpleBroker("/topic");
        // prefix gửi từ frontend -> backend (nếu có)
        config.setApplicationDestinationPrefixes("/app");
    }
}

