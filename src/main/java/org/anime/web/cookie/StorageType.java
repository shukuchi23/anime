package org.anime.web.cookie;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public enum StorageType {
  COOKIE("cookie"),
  LOCAL_STORAGE("local_storage"),
  SESSION_STORAGE("session_storage");

  private String name;
  StorageType(String session_storage) {
    name = session_storage;
  }

  @Override
  public String toString() {
    return name;
  }
}
