package org.anime.web;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public class CookieWorker extends StorageWorker {

  public CookieWorker(WebDriver driver) {
    super(driver);
  }

  public LocalStorage getLocalStorage() {
    return ((WebStorage) new Augmenter().augment(webDriver)).getLocalStorage();
  }

  public SessionStorage getSessionStorage() {
    return ((WebStorage) new Augmenter().augment(webDriver)).getSessionStorage();
  }

  @Override
  public void reloadEntity(Object entity) {
    Cookie cookie;
    assert entity instanceof Cookie;
    cookie = (Cookie) entity;

    driver.manage().addCookie(cookie);

  }
}
