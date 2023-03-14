package ru.annenkov.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class FileReadUtils extends BaseOperations {

  /**
   * Метод позволяет читать json файлы и десериализовывать их в java-объекты.
   *
   * @param filePath путь к json файлу из classpath.<br>
   *                 Например, если файл test.json лежит в папке, помеченной как Test Resource Root, то аргумент будет
   *                 выглядеть как "/test.json"
   * @param type     класс Class объекта, в который нужно десериализовать json.
   * @param <V>      класс, объект которого будет возвращен этим методом.
   * @return объект заданного вторым параметром класса, десериализованный из json.
   */
  public static <V> V readFromJsonFile (final String filePath, final Class<V> type) {
    final String content = readFile(filePath);
    try {
      return objectMapper.readValue(content, type);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Метод позволяет читать json файлы и десериализовывать их в java-объекты.
   *
   * @param filePath      путь к json файлу из classpath.<br>
   *                      Например, если файл test.json лежит в папке, помеченной как Test Resource Root, то аргумент будет
   *                      выглядеть как "/test.json"
   * @param typeReference обёртка над типом, в который нужно десериализовать json.
   * @param <V>           класс, объект которого будет возвращен этим методом.
   * @return объект типа дженерика, заданного вторым параметром, десериализованный из json.
   */
  public static <V> V readFromJsonFile (final String filePath, final TypeReference<V> typeReference) {
    final String content = readFile(filePath);
    try {
      return objectMapper.readValue(content, typeReference);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Метод позволяет читать сериализованные gRPC и десериализовывать их в java-объекты.
   *
   * @param filePath путь к json файлу из classpath.<br>
   *                 Например, если файл test.json лежит в папке, помеченной как Test Resource Root, то аргумент будет
   *                 выглядеть как "/test.json"
   * @param builder  билдер gRPC сообщения, в которое будет проводиться десериализация.
   * @param <V>      класс, объект которого будет возвращен этим методом.
   * @return объект того типа, из которого вызван билдер, заданный вторым параметром, десериализованный из json.
   */
  public static <V extends Message> V readFromJsonFile (final String filePath, final Message.Builder builder) {
    try {
      JsonFormat.parser().ignoringUnknownFields().merge(readFile(filePath), builder);
      return (V) builder.build();
    } catch (InvalidProtocolBufferException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private FileReadUtils () {}
}
