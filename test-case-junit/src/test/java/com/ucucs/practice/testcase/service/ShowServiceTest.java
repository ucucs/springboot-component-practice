package com.ucucs.practice.testcase.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.practice.testcase.base.JsonDataType;
import com.ucucs.practice.testcase.base.JsonFileSource;
import com.ucucs.practice.testcase.model.UserVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Show Service Test")
class ShowServiceTest {

  @Autowired
  private MockMvc mockMvc;

  protected MockMvc getMockMvc() {
    return mockMvc;
  }

  @Test
  void getShowLable() {
  }

  @ParameterizedTest
  @JsonFileSource(resources = "/testData.json", dataType = JsonDataType.SINGLE_JSON_NODE)
  void testSave(JsonNode testData) throws Exception {
    System.out.println(testData);
    UserVO param = new UserVO();
    param.setName(testData.get("name").asText());
    param.setNumber(testData.get("number").asInt());

    ResultActions resultActions = this.getMockMvc()
        .perform(post("/test", "a=1").contentType(MediaType.APPLICATION_JSON)
            .content(testData.toString()).accept(MediaType.ALL))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print());
    resultActions.andReturn();
  }

}