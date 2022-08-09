package br.com.emendes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.print("Digite sua api key: ");
      String apiKey = input.nextLine();

      try {
          URI uri = new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey));
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

          HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          String body = response.body();

          System.out.println(body);
      } catch (Exception e) {
          System.err.println("Não foi possivel fazer a requisição");
      }
  }
}
