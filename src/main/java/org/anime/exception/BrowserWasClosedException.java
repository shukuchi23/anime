package org.anime.exception;

public class BrowserWasClosedException extends RuntimeException{
  public BrowserWasClosedException(){
    super();
  }
  public BrowserWasClosedException(String string){
    super(string);
  }
  public BrowserWasClosedException(String format, Object... arg){
    super(String.format(format, arg));
  }
}
