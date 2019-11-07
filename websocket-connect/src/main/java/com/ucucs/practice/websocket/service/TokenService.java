package com.ucucs.practice.websocket.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

  public boolean validate(String token) {
    if (token.length() > 5) {
      return true;
    }
    return false;
  }

}
