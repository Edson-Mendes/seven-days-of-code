package br.com.emendes.parser;

import java.io.IOException;

public interface JsonParser<T> {

  T parse() throws IOException;

}
