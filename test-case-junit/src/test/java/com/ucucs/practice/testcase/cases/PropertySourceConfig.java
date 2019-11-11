package com.ucucs.practice.testcase.cases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceConfig {

  @Value("${ucs.system.version:default}")
  private String systemVersion;

  @Value("${ucs.system.other:default}")
  private String systemOther;

  public String getSystemVersion() {
    return systemVersion;
  }

  public void setSystemVersion(String systemVersion) {
    this.systemVersion = systemVersion;
  }

  public String getSystemOther() {
    return systemOther;
  }

  public void setSystemOther(String systemOther) {
    this.systemOther = systemOther;
  }
}
