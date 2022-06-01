package org.anime.events;

import java.util.concurrent.Callable;

/**
 * @author Karimov Evgeniy
 * 31.05.2022
 */
public interface EventHandler<E extends Event>  {
  void eventHandle(E event);
}
