package com.ucucs.practice.buildinfo.env;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class AppEnvironmentPostProcessor implements EnvironmentPostProcessor {

  private final String APP_ENV_FILE_PATTERN = "classpath:appenv*.properties";

  //Properties对象
  private final Properties properties = new Properties();

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {
    Resource[] resources = loadAppEnvResource();
    PropertySource<?> propertySource;
    for (Resource resource : resources) {
      propertySource = loadPropertySource(resource);
      environment.getPropertySources().addLast(propertySource);
    }
  }

  private Resource[] loadAppEnvResource() {
    try {
      new Date();
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      return resolver.getResources(APP_ENV_FILE_PATTERN);
    } catch (IOException ex) {
      throw new IllegalStateException(
          "Failed to load appenv file by pattern " + APP_ENV_FILE_PATTERN, ex);
    }
  }

  private PropertySource<?> loadPropertySource(Resource path) {
    if (!path.exists()) {
      throw new IllegalArgumentException("Resource " + path + " does not exist");
    }

    try {
      properties.load(path.getInputStream());
      return new PropertiesPropertySource(path.getFilename(), properties);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to load appenv configuration from " + path, ex);
    }
  }
}
