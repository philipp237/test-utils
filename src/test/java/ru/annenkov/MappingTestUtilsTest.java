package ru.annenkov;

import org.junit.jupiter.api.Test;
import ru.annenkov.mapper.AssertionsUtils;
import ru.annenkov.mapperTestObjects.FirstClass;
import ru.annenkov.mapperTestObjects.SecondClass;

import java.util.List;
import java.util.Map;

public class MappingTestUtilsTest {

  @Test
  void test_map() {
    FirstClass firstClass = new FirstClass();
    SecondClass e1 = new SecondClass();
    e1.string = "e1";
    SecondClass e2 = new SecondClass();
    e2.string = "e2";
    firstClass.secondClasses = List.of(e1, e2);
    firstClass.secondClassMap = Map.of(1, e1,
                                       2, e2);

    AssertionsUtils.assertEqualsToJsonFile(firstClass, "/firstClass.json");
  }

}
