package com.ucucs.practice.testcase.cases;

import com.ucucs.practice.testcase.model.Foo;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

//@TestConfiguration是专门用于测试的
@TestConfiguration
public class TestConfig {

  @Bean
  public Foo foo() {
    return new Foo("from config");
  }

}
