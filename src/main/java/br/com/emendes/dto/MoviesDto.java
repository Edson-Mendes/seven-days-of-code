package br.com.emendes.dto;

import br.com.emendes.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class MoviesDto {
  @JsonProperty("items")
  private List<Movie> movies;
  private String errorMessage;

  public boolean hasError(){
    return !errorMessage.isEmpty();
  }
}
