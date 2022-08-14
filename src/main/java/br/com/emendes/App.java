package br.com.emendes;

import br.com.emendes.client.ImdbAPIClient;
import br.com.emendes.dto.MoviesDto;
import br.com.emendes.generator.HTMLGenerator;
import br.com.emendes.parser.ImdbMovieDtoJsonParser;
import br.com.emendes.parser.JsonParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {

  private static final String PATH_VIEW = "src/main/java/br/com/emendes/view/";

  public static void main(String[] args) throws IOException {
    String uriTop250Movies = "https://imdb-api.com/en/API/Top250Movies/";
    String uriTop250TVs = "https://imdb-api.com/en/API/Top250TVs/";
    String uriInTheaters = "https://imdb-api.com/en/API/InTheaters/";
    String uriComingSoon = "https://imdb-api.com/en/API/ComingSoon/";
    String uriMostPopularTVs = "https://imdb-api.com/en/API/MostPopularTVs/";
    String uriMostPopularMovies = "https://imdb-api.com/en/API/MostPopularMovies/";

//    Chamada da API
    ImdbAPIClient client = new ImdbAPIClient(uriTop250Movies, requestUserApiKey());
    String body = client.getBody();

//    Parse do json
    JsonParser<MoviesDto> jsonParser = new ImdbMovieDtoJsonParser(body);
    MoviesDto moviesDto = jsonParser.parse();

    if (moviesDto.hasError()){
      System.err.println(moviesDto.getErrorMessage());
    } else {
      moviesDto.getItems().forEach(System.out::println);
      try (PrintWriter printWriter = new PrintWriter(PATH_VIEW + "index.html")) {
        HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);
//        Gerar HTML
        htmlGenerator.generate(moviesDto.getItems());
      } catch (Exception ex) {
        System.err.println("Something went wrong!");
      }
    }
  }

  private static String requestUserApiKey() {
    Scanner input = new Scanner(System.in);
    System.out.print("Digite sua api key: ");
    return input.nextLine();
  }

}
