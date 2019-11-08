package com.ucucs.practice.websocket.handler;

import com.ucucs.practice.websocket.constant.AppConstant;
import com.ucucs.practice.websocket.manage.SocketManager;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class OnlineWebSocketHandler extends TextWebSocketHandler {

  private static final Logger logger = LoggerFactory.getLogger(OnlineWebSocketHandler.class);

  private Integer userId;
  private WebSocketSession session;

  public static void sendMessage(Integer userId, String message) throws IOException {
    if (SocketManager.containsKey(userId.toString())) {
      SocketManager.get(userId.toString()).sendMessage(new TextMessage(message));
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    this.userId = (Integer) session.getAttributes().get(AppConstant.USER_ID);
    this.session = session;
    SocketManager.add(this.userId.toString(), this.session);
    logger.info("add session, userId={}, total={}", this.userId, SocketManager.size());
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    SocketManager.remove(this.userId.toString());
    logger.info("remove session, userId={}, total={}", this.userId, SocketManager.size());
  }

  protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    logger.info(message.getPayload());
  }


}
