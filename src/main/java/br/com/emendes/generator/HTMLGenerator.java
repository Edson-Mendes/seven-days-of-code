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
      writer.write(html(movies));
    }catch (IOException ioex){
      System.out.println("Erro ao gerar HTML!");
    }
  }

  private String html(List<Movie> movies) {
    return String.format(
        """
        <!DOCTYPE html>
        <html lang="pt-br">
          %s
          %s
        </html>
        """, head("Top250Movies"), body(movies));
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

  private String body(List<Movie> movies){
    StringBuilder cards = new StringBuilder();

    movies.forEach(m -> cards.append(card(m)));

    return String.format(
        """
          <body>
            <main>
              %s
            </main>
          </body>
        """, cards);
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
