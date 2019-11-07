package com.ucucs.practice.testcase.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ucucs.practice.testcase.model.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
public class ConfigurationTest {

  @Autowired
  private Foo foo;

  @Test
  public void testPlusCount() {
    assertEquals(foo.getName(), "from config");
  }

}
