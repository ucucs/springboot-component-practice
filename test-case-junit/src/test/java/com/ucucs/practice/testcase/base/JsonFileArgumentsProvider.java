package com.ucucs.practice.testcase.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.commons.util.Preconditions;

public class JsonFileArgumentsProvider implements ArgumentsProvider,
    AnnotationConsumer<JsonFileSource> {

  private final BiFunction<Class<?>, String, InputStream> inputStreamProvider;

  private JsonFileSource annotation;

  private String[] resources;

  private Charset charset;

  JsonFileArgumentsProvider() {
    this(Class::getResourceAsStream);
  }

  JsonFileArgumentsProvider(BiFunction<Class<?>, String, InputStream> inputStreamProvider) {
    this.inputStreamProvider = inputStreamProvider;
  }

  @Override
  public void accept(JsonFileSource acceptAnnotation) {
    this.annotation = acceptAnnotation;
    resources = acceptAnnotation.resources();
    try {
      this.charset = Charset.forName(acceptAnnotation.encoding());
    } catch (Exception ex) {
      throw new PreconditionViolationException(
          "The charset supplied in " + this.annotation + " is invalid", ex);
    }
  }

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    return Arrays.stream(resources).map(resource -> openInputStream(context, resource))
        .flatMap(this::toStream);
  }

  private Stream<Arguments> toStream(InputStream inputStream) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode arrNode = objectMapper.readTree(readJSON(inputStream));

      if (JsonDataType.SINGLE_JSON_NODE.equals(annotation.dataType())) {
        return Stream.of(Arguments.of(arrNode));
      }

      if (!arrNode.isArray()) {
        throw new IllegalStateException("DataType use array,but data is not array");
      }

      List<JsonNode> objList = new ArrayList<>();
      for (final JsonNode objNode : arrNode) {
        objList.add(objNode);
      }

      return Arrays.stream(objList.toArray()).map((obj) -> Arguments.of(new Object[]{obj}));
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse json from stream", ex);
    }
  }

  /**
   * 读取文件到字符串
   *
   * @return 文件内容
   */
  private String readJSON(InputStream inputStream) {
    StringBuilder objFile = new StringBuilder(1000);
    // 读取系统换行符
    BufferedReader objReader;
    try {
      objReader = new BufferedReader(new InputStreamReader(inputStream, charset));
      String strLine = objReader.readLine();
      while (strLine != null) {
        objFile.append(strLine).append(System.getProperty("line.separator"));
        strLine = objReader.readLine();
      }
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to read json file from stream", ex);
    }
    return objFile.toString();
  }

  private InputStream openInputStream(ExtensionContext context, String resource) {
    Preconditions
        .notBlank(resource, "Classpath resource [" + resource + "] must not be null or blank");
    Class<?> testClass = context.getRequiredTestClass();
    return Preconditions.notNull(inputStreamProvider.apply(testClass, resource),
        () -> "Classpath resource [" + resource + "] does not exist");
  }

  public JsonFileSource getAnnotation() {
    return annotation;
  }

  public void setAnnotation(JsonFileSource annotation) {
    this.annotation = annotation;
  }

  public String[] getResources() {
    return resources;
  }

  public void setResources(String[] resources) {
    this.resources = resources;
  }

  public Charset getCharset() {
    return charset;
  }

  public void setCharset(Charset charset) {
    this.charset = charset;
  }

  public BiFunction<Class<?>, String, InputStream> getInputStreamProvider() {
    return inputStreamProvider;
  }

}
