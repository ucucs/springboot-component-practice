package com.ucucs.practice.testcase.base;

public enum JsonDataType {
  // 单独对象(单节点,数组节点,统一返回为JsonNode)
  SINGLE_JSON_NODE,
  // 多用例对象(用数组包装多个用例对象)
  ARRAY_CASE_NODE
}