package br.com.emendes.generator;

import br.com.emendes.model.Movie;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@RequiredArgsConstructor
public class HTMLGenerator {

  private final Writer writer;

  public void generate(List<Movie> movies){
    try{
      writer.write(html());
    }catch (IOException ioex){
      System.out.println("Erro ao gerar HTML!");
    }
  }

  private String html() {
    return String.format(
        """
        <!DOCTYPE html>
        <html lang="pt-br">
          %s
          %s
        </html>
        """, head("Minha Página"), body());
  }

  private String head(String title) {
    return String.format(
        """
          <head>
              <meta charset="UTF-8">
              <meta name="viewport" content="width=device-width, initial-scale=1.0">
              <meta http-equiv="X-UA-Compatible" content="ie=edge">
              <link rel="stylesheet" href="css/reset.css">
              <link rel="stylesheet" href="css/style.css">
              <title>%s</title>
            </head>
        """, title);
  }

  private String body(){
    Movie movie = Movie.builder()
        .id("xalala")
        .title("The Dark Knight")
        .urlImage("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_Ratio0.6716_AL_.jpg")
        .year("2008")
        .rating("9.0")
        .build();

    return String.format(
        """
          <body>
            %s
          </body>
        """, card(movie));
  }


  private String card(Movie movie){
    return String.format(
        """
          <div class="card">
              <h3 class="card__title">%s</h3>
              <img class="card__image" src="%s" alt="Cartaz do filme %s"></img>
              <p class="card__info">Rating: %s - Year: %s</p>
          </div>
        """, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getRating(), movie.getYear());
  }

}
