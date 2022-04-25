package service;

import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.function.Consumer;

public class AnimeIteratorImpl implements AnimeIterator {

  @Override
  public void close() throws IOException {

  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public WebElement next() {
    return null;
  }

  @Override
  public void remove() {

  }

  @Override
  public void forEachRemaining(Consumer<? super WebElement> action) {
    AnimeIterator.super.forEachRemaining(action);
  }
}
