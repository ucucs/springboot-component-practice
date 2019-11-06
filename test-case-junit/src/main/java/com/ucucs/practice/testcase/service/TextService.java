package com.ucucs.practice.testcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TextService {

  private static final Logger logger = LoggerFactory.getLogger(TextService.class);

  private static final String ORIGINAL_OUTPUT = "Original Text Service Text";

  public String getText() {
    logger.info("get Text");
    return ORIGINAL_OUTPUT;
  }

}
