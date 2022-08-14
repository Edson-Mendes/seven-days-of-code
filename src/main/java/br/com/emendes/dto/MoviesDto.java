package br.com.emendes.dto;

import br.com.emendes.model.Content;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class MoviesDto {

  private List<Content> items;
  private String errorMessage;

  public boolean hasError(){
    return !errorMessage.isEmpty();
  }
}
