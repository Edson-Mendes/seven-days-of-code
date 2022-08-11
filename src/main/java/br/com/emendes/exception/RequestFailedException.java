package br.com.emendes.exception;

public class RequestFailedException extends RuntimeException{

  public RequestFailedException(String message, Throwable cause){
    super(message, cause);
  }

}
