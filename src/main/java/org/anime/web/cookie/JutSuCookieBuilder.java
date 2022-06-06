package org.anime.web.cookie;

import org.openqa.selenium.Cookie;

import java.util.StringJoiner;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public class JutSuCookieBuilder extends StorageEntityBuilder<Cookie> {

  // cookie
  private static final String VALUE_FORMAT = ".%s|%s.";
  private static final String COOKIE_FORMAT = "[%s]";
  private static final String DOMAIN = ".jut.su";


  @Override
  protected String getValueFormat() {
    return VALUE_FORMAT;
  }

  @Override
  protected String getDomain() {
    return DOMAIN;
  }

  @Override
  protected String getCookieFormat() {
    return COOKIE_FORMAT;
  }

  public JutSuCookieBuilder(String name) {
    super(name, "!", Cookie.class);
  }

  @Override
  public Cookie build() {
    StringJoiner joiner = getJoiner();
    getValues().forEach(joiner::add);

    String value = String.format("[%s]", joiner.toString());
    return new Cookie(getName(), value);
  }

  @Override
  protected String getPath() {
    return null;
  }

//  [.aId1|aSecond1.!.aId2|aSecond2.]

}
