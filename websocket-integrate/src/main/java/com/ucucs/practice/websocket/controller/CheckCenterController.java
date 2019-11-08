package com.ucucs.practice.websocket.controller;

import com.ucucs.practice.websocket.service.WebSocketServer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkcenter")
public class CheckCenterController {

  private static final Logger logger = LoggerFactory.getLogger(CheckCenterController.class);

  @RequestMapping("/socket/push/{cid}")
  public String pushToWeb(@PathVariable String cid, String message) {
    try {
      WebSocketServer.sendInfo(message, cid);
    } catch (IOException ex) {
      logger.error("send message error", ex);
      return "Failed" + (cid + "#" + ex.getMessage());
    }
    return "Success";
  }

}
