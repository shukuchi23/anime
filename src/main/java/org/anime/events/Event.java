package org.anime.events;

/**
 * @author Karimov Evgeniy
 * 30.05.2022
 */
public interface Event {
  String getMsg();

  /*todo: 1) возвращать обработчик связанный с ивентом
  *  2) сообщать обработчику всякое
  * 3) текст ивента(для лога) */
}
