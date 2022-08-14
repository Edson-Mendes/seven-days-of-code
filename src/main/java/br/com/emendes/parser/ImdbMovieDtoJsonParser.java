package br.com.emendes.parser;

import br.com.emendes.dto.MoviesDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class ImdbMovieDtoJsonParser implements JsonParser<MoviesDto> {

  private String json;

  @Override
  public MoviesDto parse() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, MoviesDto.class);
  }

}
