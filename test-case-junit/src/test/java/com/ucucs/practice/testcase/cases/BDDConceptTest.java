package com.ucucs.practice.testcase.cases;

import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * BDD概念
 */
public class BDDConceptTest {

  @Test
  void test() {
    // BDD(Behavior Driven Development): Given When Then
    List<String> data = Mockito.mock(List.class);

    // Given
    BDDMockito.given(data.size()).willReturn(10);

    // When
    int actual = data.size();

    // Then
    MatcherAssert.assertThat(actual, CoreMatchers.equalTo(10));
  }

}
