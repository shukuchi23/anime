package org.anime.utils;

import java.time.LocalTime;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
public class TypeUtils {
  public static LocalTime localTimeFromString(String str){
    final String[] split = str.split(":");
    final int minute = Integer.parseInt(split[0]);
    final int sec = Integer.parseInt(split[1]);
    return LocalTime.of(0, minute, sec);
  }
}
