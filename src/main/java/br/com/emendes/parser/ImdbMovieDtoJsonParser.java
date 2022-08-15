package br.com.emendes.parser;

import br.com.emendes.dto.IMDbDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class ImdbMovieDtoJsonParser implements JsonParser<IMDbDto> {

  private String json;

  @Override
  public IMDbDto parse() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, IMDbDto.class);
  }

}
