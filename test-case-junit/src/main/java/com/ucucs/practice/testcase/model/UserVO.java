package com.ucucs.practice.testcase.model;

import java.io.Serializable;

public class UserVO implements Serializable {

  private String name;
  private Integer number;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }
}
