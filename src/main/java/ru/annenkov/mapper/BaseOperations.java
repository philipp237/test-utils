package ru.annenkov.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class BaseOperations {

  protected final static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.setSerializationInclusion(NON_NULL);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
    objectMapper.setDefaultPrettyPrinter(new EditedPrettyPrinter());
  }

  protected static String readFile (final String path) {
    try {
      final URL resource = FileReadUtils.class.getResource(path);
      return Files.readString(Paths.get(resource.toURI()));
    } catch (URISyntaxException | IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private static class EditedPrettyPrinter extends DefaultPrettyPrinter {

    public EditedPrettyPrinter () {
      this._objectIndenter = new DefaultIndenter("  ", "\n");
      this._arrayIndenter = this._objectIndenter;
    }

    @Override
    public DefaultPrettyPrinter createInstance () {
      return new EditedPrettyPrinter();
    }

    @Override
    public void writeObjectFieldValueSeparator (final JsonGenerator jsonGenerator) throws IOException {
      jsonGenerator.writeRaw(": ");
    }
  }

}
