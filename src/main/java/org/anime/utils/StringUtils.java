package org.anime.utils;

/**
 * @author Karimov Evgeniy
 * 01.06.2022
 */
public class StringUtils {
  public static final String SHORT_LOG_FORMAT = "-- [%s]";
  public static final String FULL_LOG_FORMAT = SHORT_LOG_FORMAT + " - %s\n";

  public static void log(String eventName, String actionMsg) {
    if (actionMsg.isEmpty())
      System.out.printf(SHORT_LOG_FORMAT + "\n" , eventName);
    else
      System.out.printf(FULL_LOG_FORMAT, eventName, actionMsg);
  }

  public static void log(String eventName) {
    log(eventName, "");
  }
}
