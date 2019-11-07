package com.ucucs.practice.testcase.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Home Controller Extra Test")
class HomeControllerExtraTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Index Content Extra Test")
  void index() throws Exception {
    String body = this.restTemplate.getForObject("/", String.class);
    assertEquals(body, "Hello World!");
  }

}