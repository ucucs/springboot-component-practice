package com.ucucs.practice.testcase.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@DisplayName("Case Service Test")
@ActiveProfiles("test")
public class PropertySourceTest {

  @Autowired
  private PropertySourceConfig propertySourceConfig;

  @Test
  public void testExtraConfig() {
    assertEquals("ver", propertySourceConfig.getSystemOther());
    assertEquals("ver 1.0", propertySourceConfig.getSystemVersion());
  }

}
