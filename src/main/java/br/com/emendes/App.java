package br.com.emendes;

import br.com.emendes.dto.MoviesDto;
import br.com.emendes.exception.RequestFailedException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws IOException {
    String body = sendRequest();

    MoviesDto moviesDto = parseJsonToMoviesDto(body);

    if (moviesDto.hasError()){
      System.err.println(moviesDto.getErrorMessage());
    } else {
      moviesDto.getMovies().forEach(System.out::println);
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
