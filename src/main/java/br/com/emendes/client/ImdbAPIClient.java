package br.com.emendes.client;

import br.com.emendes.exception.RequestFailedException;
import lombok.AllArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@AllArgsConstructor
public class ImdbAPIClient implements APIClient{

  private final String uri;
  private final String apiKey;

  /**
   * Busca o corpo da requisição.
   * @return Uma String que representa o body da requisição.
   * @throws RequestFailedException se o status code da requisição não for 200.
   */
  public String getBody(){
    HttpResponse<String> response = sendRequest();
    if (response.statusCode() != 200){
      throw new RequestFailedException(String.format("status code não é 200, status code: %d", response.statusCode()));
    }
    return response.body();
  }

  /**
   * Realiza uma requisição para a URI + apiKey fornecidos.
   * @return HttpResponse que representa a resposta da requisição.
   * @throws RequestFailedException se houver alguma exception na requisição.
   */
  private HttpResponse<String> sendRequest() {
    try {
      URI uri = new URI(this.uri + apiKey);
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception ex) {
      throw new RequestFailedException("Não foi possível executar a requisição!", ex);
    }
  }



}
