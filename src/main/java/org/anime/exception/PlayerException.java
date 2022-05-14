package org.anime.exception;

public class PlayerException extends RuntimeException{
  public PlayerException() {
  }

  public PlayerException(String message) {
    super(message);
  }

  public PlayerException(String format, Object... arg){
    super(String.format(format, arg));
  }
}
