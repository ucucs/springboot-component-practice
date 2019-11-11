package com.ucucs.practice.testcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SingleService {

  private static final Logger logger = LoggerFactory.getLogger(SingleService.class);

  public String getShowLableParam(String key) {
    if (StringUtils.hasText(key)) {
      return "Show Key:" + key;
    }
    return "Show Key Empty";
  }

}
