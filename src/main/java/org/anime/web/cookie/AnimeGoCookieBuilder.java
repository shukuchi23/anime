package org.anime.web.cookie;

import org.openqa.selenium.html5.LocalStorage;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public class AnimeGoCookieBuilder extends StorageEntityBuilder<LocalStorage> {
  // localstorage https://kodik.info
  // id1, seconds
  private final String VALUE_FORMAT = "\"%s\" : {\"p\" : %d}";
  private final String COOKIE_FORMAT = "{%s}";
  private static final String DOMAIN = ".jut.su";

  public AnimeGoCookieBuilder(String name) {
    super(name, ",", LocalStorage.class);
  }

  @Override
  protected String getValueFormat() {
    return VALUE_FORMAT;
  }

  @Override
  protected String getDomain() {
    return null;
  }

  @Override
  protected String getCookieFormat() {
    return COOKIE_FORMAT;
  }

  @Override
  protected String getPath() {
    return null;
  }


}
