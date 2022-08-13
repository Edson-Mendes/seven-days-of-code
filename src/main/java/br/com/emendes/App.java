package br.com.emendes;

import br.com.emendes.client.ImdbApiClient;
import br.com.emendes.dto.MoviesDto;
import br.com.emendes.generator.HTMLGenerator;
import br.com.emendes.parser.ImdbMovieDtoJsonParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {

  private static final String PATH_VIEW = "src/main/java/br/com/emendes/view/";

  public static void main(String[] args) throws IOException {
    String uriTop250Movies = "https://imdb-api.com/en/API/Top250Movies/";
    String uriTop250TVs= "https://imdb-api.com/en/API/Top250TVs/";

//    Chamada da API
    ImdbApiClient client = new ImdbApiClient(uriTop250Movies, requestUserApiKey());
    String body = client.getBody();

//    Parse do json
    MoviesDto moviesDto = new ImdbMovieDtoJsonParser(body).parse();

    if (moviesDto.hasError()){
      System.err.println(moviesDto.getErrorMessage());
    } else {
      moviesDto.getMovies().forEach(System.out::println);
      try (PrintWriter printWriter = new PrintWriter(PATH_VIEW + "index.html")) {
        HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);
//        Gerar HTML
        htmlGenerator.generate(moviesDto.getMovies());
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
