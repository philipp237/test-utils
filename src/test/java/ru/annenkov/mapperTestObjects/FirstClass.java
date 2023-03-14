package ru.annenkov.mapperTestObjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FirstClass {
  public byte byteField;
  public short shortField;
  public int intField;
  public long longField;
  public float floatField;
  public double doubleField;
  public boolean booleanField;
  public char charField;
  public Byte byteWrapperField;
  public Short shortWrapperField;
  public Integer integerWrapperField;
  public Long longWrapperField;
  public Float floatWrapperField;
  public Double doubleWrapperField;
  public Boolean booleanWrapperField;
  public Character characterWrapperField;
  public UUID uuid;
  public List<SecondClass> secondClasses;
  public Map<Integer, SecondClass> secondClassMap;
}
