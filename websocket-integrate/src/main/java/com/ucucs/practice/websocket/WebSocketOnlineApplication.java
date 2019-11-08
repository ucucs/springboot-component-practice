package com.ucucs.practice.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication
public class WebSocketOnlineApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebSocketOnlineApplication.class, args);
  }

}
