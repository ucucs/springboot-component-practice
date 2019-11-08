package com.ucucs.practice.websocket.config;

import com.ucucs.practice.websocket.handler.OnlineWebSocketHandler;
import com.ucucs.practice.websocket.interceptor.OnlineHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(onlineWebSocketHandler(), "/websocket-storm").setAllowedOrigins("*")
        .addInterceptors(onlineHandshakeInterceptor()).withSockJS();

    registry.addHandler(onlineWebSocketHandler(), "/websocket").setAllowedOrigins("*")
        .addInterceptors(onlineHandshakeInterceptor());
  }

  @Bean
  public WebSocketHandler onlineWebSocketHandler() {
    return new PerConnectionWebSocketHandler(OnlineWebSocketHandler.class);
  }

  @Bean
  public HandshakeInterceptor onlineHandshakeInterceptor() {
    return new OnlineHandshakeInterceptor();
  }

  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }

}
