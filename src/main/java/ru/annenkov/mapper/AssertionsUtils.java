package ru.annenkov.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionsUtils extends BaseOperations {

  /**
   * Метод позволяет сравнить действительный объект с ожидаемым, записанным в качестве json.
   *
   * @param actualResult           действительный результат.
   * @param expectedResultFilePath путь из classpath до json файла, содержащего в себе ожидаемый результат.<br>
   *                               Например, если файл test.json лежит в папке, помеченной как Test Resource Root, то
   *                               аргумент будет выглядеть как "/test.json"
   */
  public static void assertEqualsToJsonFile (final Object actualResult, final String expectedResultFilePath) {
    final String expectedResultAsJson = readFile(expectedResultFilePath);
    String actualResultAsJson = null;
    try {
      if (actualResult instanceof Message) {
        actualResultAsJson = JsonFormat.printer().print((Message) actualResult);
      } else {
        actualResultAsJson = objectMapper.writeValueAsString(actualResult);
      }
      final JsonNode expectedObject = objectMapper.readTree(expectedResultAsJson);
      final JsonNode actualObject = objectMapper.readTree(actualResultAsJson);

      assertEquals(expectedObject, actualObject);
    } catch (final AssertionError e) {
      assertEquals(expectedResultAsJson, actualResultAsJson);
    } catch (final InvalidProtocolBufferException | JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private AssertionsUtils () {}
}
