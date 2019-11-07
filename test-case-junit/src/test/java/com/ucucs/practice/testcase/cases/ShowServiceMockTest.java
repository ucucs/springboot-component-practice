package com.ucucs.practice.testcase.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.ucucs.practice.testcase.service.ShowService;
import com.ucucs.practice.testcase.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
@DisplayName("Case Service Test")
public class ShowServiceMockTest {

  //这个个人觉得没啥作用，除非是无依赖的单独方法，这个会把其他的办法都设置为null
  @MockBean
  private ShowService showService;

  //这个只替换指定的方法，其余的保持
  @SpyBean
  private TextService spyTestService;

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

    String testKey = null;
    given(spyTestService.getShowLableParam(testKey)).will((InvocationOnMock invocation) -> {
      if ("testKey".equals(invocation.getArgument(0))) {
        return "Show Key Not Empty";
      }

      return "Show Key Empty Now";
    });

    bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Empty Now");

    testKey = "testKey";
    bddResult = spyTestService.getShowLableParam(testKey);
    assertEquals(bddResult, "Show Key Not Empty");
  }

}
