package br.com.emendes;

import br.com.emendes.model.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws IOException {
    String body = sendRequest();

    List<Movie> movies = parseJsonMovies(body);

    System.out.println(titles(movies));
    System.out.println(urlImages(movies));
    System.out.println(years(movies));
    System.out.println(imdbRatings(movies));
  }

  public static String sendRequest() {
    try {
      URI uri = new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", requestUserApiKey()));
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (Exception ex) {
      throw new RuntimeException("Não foi possível executar a requisição!", ex);
    }
  }

  private static String requestUserApiKey() {
    Scanner input = new Scanner(System.in);
    System.out.print("Digite sua api key: ");
    return input.nextLine();
  }

  public static List<Movie> parseJsonMovies(String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(json).get("items");

    return mapper.readValue(jsonNode.toString(), new TypeReference<List<Movie>>() {});
  }

  public static List<String> titles(List<Movie> movies){
    return movies.stream().map(Movie::getTitle).toList();
  }

  public static List<String> urlImages(List<Movie> movies){
    return movies.stream().map(Movie::getUrlImage).toList();
  }

  public static List<String> years(List<Movie> movies){
    return movies.stream().map(Movie::getYear).toList();
  }

  public static List<String> imdbRatings(List<Movie> movies){
    return movies.stream().map(Movie::getRating).toList();
  }
}
