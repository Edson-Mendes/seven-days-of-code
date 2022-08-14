package br.com.emendes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Movie implements Content {

  private String id;
  private String title;
  private String year;
  @JsonProperty("image")
  private String urlImage;
  @JsonProperty("imDbRating")
  private String rating;

}
