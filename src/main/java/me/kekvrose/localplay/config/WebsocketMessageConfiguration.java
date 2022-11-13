package me.kekvrose.localplay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebsocketMessageConfiguration implements WebSocketMessageBrokerConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketMessageConfiguration.class);

    

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
        registry.enableSimpleBroker("/session");
        registry.setApplicationDestinationPrefixes("/");
    }



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        registry.addEndpoint("/sock").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/sock").setAllowedOrigins("*");
    }
    
}
