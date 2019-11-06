package com.ucucs.practice.testcase.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.practice.testcase.base.JsonDataType;
import com.ucucs.practice.testcase.base.JsonFileSource;
import org.junit.jupiter.params.ParameterizedTest;

public class JsonSourceTest {

  @ParameterizedTest
  @JsonFileSource(resources = "/testData.json", dataType = JsonDataType.SINGLE_JSON_NODE)
  void testSave(JsonNode testData) throws Exception {
    assertNotNull(testData);
  }

}
