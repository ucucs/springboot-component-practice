package com.ucucs.practice.testcase.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ucucs.practice.testcase.service.ShowService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Case Service Test")
public class ShowServiceTest {

  @Autowired
  private ShowService showService;

  @BeforeEach
  public void testBefore() {
    System.out.println("before");
  }

  @AfterEach
  public void testAfter() {
    System.out.println("after");
  }

  @Test
  public void testSimpleService() {
    String showLable = showService.getShowLable();
    assertEquals(showLable, "Original Text Service Text");
  }

}
