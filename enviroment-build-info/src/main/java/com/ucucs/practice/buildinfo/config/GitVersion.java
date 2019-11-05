package com.ucucs.practice.buildinfo.config;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"git.properties"})
public class GitVersion implements Serializable {

  @Value("${git.branch}")
  private String branch;

  @Value("${git.build.time}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
  private Date buildTime;

  @Value("${git.commit.id}")
  private String commitId;

  @Value("${git.commit.id.abbrev}")
  private String commitIdAbbrev;

  @Value("${git.commit.time}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
  private Date commitTime;

  @Value("${git.commit.user.name}")
  private String commitUserName;

  @Value("${git.tags}")
  private String tags;

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public Date getBuildTime() {
    return buildTime;
  }

  public void setBuildTime(Date buildTime) {
    this.buildTime = buildTime;
  }

  public String getCommitId() {
    return commitId;
  }

  public void setCommitId(String commitId) {
    this.commitId = commitId;
  }

  public String getCommitIdAbbrev() {
    return commitIdAbbrev;
  }

  public void setCommitIdAbbrev(String commitIdAbbrev) {
    this.commitIdAbbrev = commitIdAbbrev;
  }

  public Date getCommitTime() {
    return commitTime;
  }

  public void setCommitTime(Date commitTime) {
    this.commitTime = commitTime;
  }

  public String getCommitUserName() {
    return commitUserName;
  }

  public void setCommitUserName(String commitUserName) {
    this.commitUserName = commitUserName;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }
}
