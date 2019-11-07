package com.ucucs.practice.websocket.config;

import com.ucucs.practice.websocket.handler.AppWebSocketHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@Configuration
public class WebSocketConfiguration {

  @Bean
  public AppWebSocketHandler appWebSocketHandler() {
    return new AppWebSocketHandler();
  }

  @Bean
  public HandlerMapping webSocketMapping() {
    /**
     * 使用 map 指定 WebSocket 协议的路由，路由为 ws://localhost:8080/echo
     */
    final Map<String, WebSocketHandler> map = new HashMap<>();
    map.put("/echo", appWebSocketHandler());

    /**
     * SimpleUrlHandlerMapping 指定了 WebSocket 的路由配置
     */
    final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
    mapping.setUrlMap(map);
    return mapping;
  }

  /**
   * WebSocketHandlerAdapter 负责将 EchoHandler 处理类适配到 WebFlux 容器中
   */
  @Bean
  public WebSocketHandlerAdapter handlerAdapter() {
    return new WebSocketHandlerAdapter();
  }

}
