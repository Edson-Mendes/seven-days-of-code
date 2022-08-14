package br.com.emendes.model;

import br.com.emendes.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( use = JsonTypeInfo.Id.DEDUCTION )
@JsonSubTypes({ @JsonSubTypes.Type(value = Movie.class) })
@JsonIgnoreProperties(ignoreUnknown = true)
public interface Content {

  String getTitle();
  String getUrlImage();
  String getRating();
  String getYear();

}
