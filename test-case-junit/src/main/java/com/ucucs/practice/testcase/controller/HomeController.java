package com.ucucs.practice.testcase.controller;

import com.ucucs.practice.testcase.model.UserVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @RequestMapping("/")
  public String index() {
    return "Hello World!";
  }

  @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object test(@RequestBody UserVO userVO) {
    return "Hello Test!" + userVO.getName() + userVO.getNumber();
  }

}
