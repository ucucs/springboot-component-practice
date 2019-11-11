package com.ucucs.practice.websocket.manage;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class SocketManager {

  private static final Logger logger = LoggerFactory.getLogger(SocketManager.class);

  private final static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

  public static void add(String key, WebSocketSession webSocketSession) {
    logger.info("新添加webSocket连接 {} ", key);
    sessionMap.put(key, webSocketSession);
  }

  public static void remove(String key) {
    logger.info("移除webSocket连接 {} ", key);
    sessionMap.remove(key);
  }

  public static WebSocketSession get(String key) {
    logger.info("获取webSocket连接 {}", key);
    return sessionMap.get(key);
  }

  public static int size() {
    return sessionMap.size();
  }

  public static boolean containsKey(String key) {
    return sessionMap.containsKey(key);
  }

  public static void sendMessage(Integer userId, String message) throws IOException {
    if (SocketManager.containsKey(userId.toString())) {
      SocketManager.get(userId.toString()).sendMessage(new TextMessage(message));
    }
  }

}
