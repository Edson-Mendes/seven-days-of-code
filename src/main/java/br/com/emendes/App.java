package br.com.emendes;

import br.com.emendes.dto.MoviesDto;
import br.com.emendes.exception.RequestFailedException;
import br.com.emendes.generator.HTMLGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {

  private static String PATH_VIEW = "src/main/java/br/com/emendes/view/";
  public static void main(String[] args) throws IOException {
    String body = sendRequest();

    MoviesDto moviesDto = parseJsonToMoviesDto(body);

    if (moviesDto.hasError()){
      System.err.println(moviesDto.getErrorMessage());
    } else {
      moviesDto.getMovies().forEach(System.out::println);
    }

    try(PrintWriter printWriter = new PrintWriter(PATH_VIEW+"index.html")){
      HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);
      htmlGenerator.generate(moviesDto.getMovies());
    }catch(Exception ex){
      System.err.println("Something went wrong!");
    }

  }

  /**
   * Solicita a lista dos Top250Movies da API do IMDb.
   * @return String que representa o body da resposta.
   * @throws RequestFailedException se houver alguma exception na requisição.
   */
  public static String sendRequest() {
//    TODO: Refatorar esse método.
    try {
      URI uri = new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", requestUserApiKey()));
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (Exception ex) {
      throw new RequestFailedException("Não foi possível executar a requisição!", ex);
    }
  }

  private static String requestUserApiKey() {
    Scanner input = new Scanner(System.in);
    System.out.print("Digite sua api key: ");
    return input.nextLine();
  }

  public static MoviesDto parseJsonToMoviesDto(String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, MoviesDto.class);
  }
}
