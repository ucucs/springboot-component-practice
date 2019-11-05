package com.ucucs.practice.buildinfo.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @Value("${appenv:default}")
  private String appEnv;

  @RequestMapping("/")
  public String index() {
    Date timeNow = new Date();
    return "Hello World!" + timeNow + appEnv;
  }

}
