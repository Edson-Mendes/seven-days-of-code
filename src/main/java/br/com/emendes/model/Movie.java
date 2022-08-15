package br.com.emendes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

  @Override
  public int compareTo(Content other) {
    return this.rating.compareTo(other.getRating());
  }

}
