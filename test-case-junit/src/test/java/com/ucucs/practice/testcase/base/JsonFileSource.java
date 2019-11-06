package com.ucucs.practice.testcase.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.params.provider.ArgumentsSource;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(JsonFileArgumentsProvider.class)
public @interface JsonFileSource {

  /**
   * JSON资源文件路基
   */
  String[] resources();

  /**
   * json文件解析编码格式
   *
   * <p>
   * Defaults to {@code "UTF-8"}.
   *
   * @see java.nio.charset.StandardCharsets
   */
  String encoding() default "UTF-8";

  /**
   * json文件是单用例还是多用例
   */
  JsonDataType dataType() default JsonDataType.SINGLE_JSON_NODE;

}