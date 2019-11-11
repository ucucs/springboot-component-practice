package com.ucucs.practice.testcase.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.ucucs.practice.testcase.service.ShowService;
import com.ucucs.practice.testcase.service.SingleService;
import com.ucucs.practice.testcase.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
@DisplayName("Case Service Test")
public class ShowServiceMockTest {

  //这个个人觉得没啥作用，除非是无依赖的单独方法
  //这个会把其他的办法都设置为null(如果同时测试多个方法时，就有问题了)
  @MockBean
  private ShowService showService;

  //这个只替换指定的方法，其余的保持
  @SpyBean
  private TextService spyTestService;

  @Autowired
  private SingleService singleService;

  @BeforeEach
  public void testBefore() {
    when(this.showService.getShowLable()).thenReturn("Mock Result");
    given(this.spyTestService.getText()).willReturn("Mock Result Spy");
  }

  @Test
  public void testSimpleService() {
    String showLable = showService.getShowLable();
    assertEquals(showLable, "Mock Result");

    // not mock method(will replace method to null)
    String showNext = showService.getShowLableNext();
    assertNull(showNext);

    // spy mock method
    String showLableSpy = spyTestService.getText();
    assertEquals(showLableSpy, "Mock Result Spy");

    // spy would keep method origin
    String showSpy = spyTestService.getTextNext();
    assertEquals(showSpy, "Text Next");

    //BDD
    given(showService.getShowLableNext()).willReturn("Hello BDD");
    String bddResult = showService.getShowLableNext();
    assertEquals(bddResult, "Hello BDD");
  }

  @Test
  public void testGivenOnce() {
    String testKey = null;
    given(spyTestService.getShowLableParam(testKey)).will((InvocationOnMock invocation) -> {
      if ("testKey".equals(invocation.getArgument(0))) {
        return "Show Key Not Empty";
      }

      return "Show Key Empty Now";
    });

    String bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Empty Now");

    //(因为你的key变了,不满足mock指定参数了)
    testKey = "testKey";
    bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key:" + testKey);
  }

  @Test
  public void testGivenTwice() {
    String testKey = null;
    given(spyTestService.getShowLableParam(testKey)).will((InvocationOnMock invocation) -> {
      if ("testKey".equals(invocation.getArgument(0))) {
        return "Show Key Not Empty";
      }

      return "Show Key Empty Now";
    });

    String bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Empty Now");

    testKey = "testKey";
    given(spyTestService.getShowLableParam(testKey)).will((InvocationOnMock invocation) -> {
      if ("testKey".equals(invocation.getArgument(0))) {
        return "Show Key Not Empty";
      }

      return "Show Key Empty Now";
    });

    bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Not Empty");

    testKey = null;
    bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Empty Now");
  }

  @Test
  public void testGivenThird() {
    String testKey = "testKey";
    given(spyTestService.getShowLableParam(testKey)).will((InvocationOnMock invocation) -> {
      if ("testKey".equals(invocation.getArgument(0))) {
        return "Show Key Not Empty";
      }

      return "Show Key Empty Now";
    });

    //这说明了一个问题，就是given的参数化，是针对参数值的，同一个参数值的mock
    String bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Not Empty");

    bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Not Empty");
  }

  @Test
  public void testNotSpyBean() {
    String testKey = null;
    given(singleService.getShowLableParam(testKey)).will((InvocationOnMock invocation) -> {
      if ("testKey".equals(invocation.getArgument(0))) {
        return "Show Key Not Empty";
      }

      return "Show Key Empty Now";
    });

    //没有使用spybean/mockbean修饰的,是不会有效果的
    String bddResult = singleService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Empty");
  }

}
