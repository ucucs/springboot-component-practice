package com.ucucs.practice.testcase.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Home Controller Test")
class HomeControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  @DisplayName("Index Content Test")
  void index() throws Exception {
    this.mvc.perform(get("/")).andExpect(status().isOk())
        .andExpect(content().string("Hello World!"))
        .andDo(MockMvcResultHandlers.print());
  }

}