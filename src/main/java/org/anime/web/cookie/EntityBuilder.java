package org.anime.web.cookie;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public interface EntityBuilder<T> {
  void setName(String name);
  String getValue();
  String getName();

  EntityBuilder<T> addValue(String id, int sec);
  default String getSeparator(){
    return ",";
  }

}
