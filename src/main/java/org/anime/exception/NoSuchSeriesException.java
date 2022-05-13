package org.anime.exception;

import java.util.NoSuchElementException;

/**
 * @author Karimov Evgeniy
 * 13.05.2022
 */
public class NoSuchSeriesException extends NoSuchElementException {
  public NoSuchSeriesException() {
  }

  public NoSuchSeriesException(String message) {
    super(message);
  }

  public NoSuchSeriesException(String format, Object... arg) {
    super(String.format(format, arg));
  }
}
