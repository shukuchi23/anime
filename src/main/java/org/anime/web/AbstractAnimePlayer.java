package org.anime.web;

public class AbstractAnimePlayer implements AutoCloseable {
  protected WebClient client;

  public AbstractAnimePlayer(WebClient client){
    this.client = client;
  }

  @Override
  public void close() throws Exception {
    client.close();
  }
}
