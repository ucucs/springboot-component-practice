package com.ucucs.practice.testcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TextService {

  private static final Logger logger = LoggerFactory.getLogger(TextService.class);

  private static final String ORIGINAL_OUTPUT = "Original Text Service Text";

  public String getText() {
    logger.info("get Text");
    return ORIGINAL_OUTPUT;
  }

  public String getTextNext() {
    return "Text Next";
  }

  public String getShowLableParam(String key) {
    if (StringUtils.hasText(key)) {
      return "Show Key:" + key;
    }
    return "Show Key Empty";
  }

}
