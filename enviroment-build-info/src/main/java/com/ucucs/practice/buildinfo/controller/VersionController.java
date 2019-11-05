package com.ucucs.practice.buildinfo.controller;

import com.ucucs.practice.buildinfo.config.GitVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

  private GitVersion gitVersion;

  public VersionController(GitVersion gitVersion) {
    this.gitVersion = gitVersion;
  }

  @GetMapping(value = "/version")
  public GitVersion versionInformation() {
    return gitVersion;
  }


}
