package com.ucucs.practice.websocket.interceptor;

import com.ucucs.practice.websocket.constant.AppConstant;
import com.ucucs.practice.websocket.service.UserService;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class OnlineHandshakeInterceptor implements HandshakeInterceptor {

  private static Logger logger = LoggerFactory.getLogger(OnlineHandshakeInterceptor.class);

  @Autowired
  private UserService userService;

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    HttpServletRequest httpRequest = ((ServletServerHttpRequest) request).getServletRequest();
    String userIdStr = httpRequest.getParameter(AppConstant.USER_ID);
    String session = httpRequest.getParameter(AppConstant.SESSION);
    if (!StringUtils.hasText(userIdStr) || !StringUtils.hasText(session)) {
      logger.info("userId and session needed, now useId={}, session={}", userIdStr, session);
      throw new AuthenticationException("未授权,拒绝连接");
    }
    int userId = Integer.valueOf(userIdStr);
    if (!userService.checkLogin(userId, session)) {
      throw new AuthenticationException("授权登陆失败");
    }
    attributes.put(AppConstant.USER_ID, userId);
    return true;
  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wsHandler, Exception exception) {
  }

}
