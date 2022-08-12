package br.com.emendes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
  private String id;
  private String title;
  private String year;
  @JsonProperty("image")
  private String urlImage;
  @JsonProperty("imDbRating")
  private String rating;
}
