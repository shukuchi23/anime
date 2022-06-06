package org.anime.web.cookie;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public abstract class StorageEntityBuilder<T> {
  private static final String COOKIE_TYPE = Cookie.class.getTypeName();
  private static final String LOCAL_STORAGE_TYPE = LocalStorage.class.getTypeName();
  private String name;
  private List<String> values;
  private StringJoiner joiner;
//  private Class<T>
//  private Class<T> type;

  protected abstract String getDomain();

  protected abstract String getValueFormat();

  protected abstract String getCookieFormat();

  public StorageEntityBuilder(String name, String separator, Class<T> storage) {
    this.name = name;
    values = new LinkedList<>();
    joiner = new StringJoiner(separator);
  }

  protected StringJoiner getJoiner() {
    return joiner;
  }

  public StorageEntityBuilder<T> addValue(String id, int seconds) {
    this.values.add(String.format(getValueFormat(), id, seconds));
    return this;
  }

  public Cookie buildAsCookie(){
    StringJoiner joiner = getJoiner();
    getValues().forEach(joiner::add);
    String value = String.format(getCookieFormat(), joiner.toString());
    return new Cookie(getName(), value, getDomain(), getPath(), null);
  }


  public abstract Cookie build();

  protected abstract String getPath();

  public String getName() {
    return name;
  }

  public List<String> getValues() {
    return values;
  }
}
