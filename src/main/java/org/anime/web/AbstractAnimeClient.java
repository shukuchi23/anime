package org.anime.web;

import org.anime.config.DriverConfig;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public abstract class AbstractAnimeClient implements AnimeInterface {
  protected WebDriver webDriver;
  protected WebDriverWait waiter;
  protected AnimeInterface animeInterface;
  protected boolean skipOpening;
  protected boolean skipEnding;

  public WebDriver getWebDriver() {
    return webDriver;
  }

  public WebDriverWait getWaiter(){
    return waiter;
  }

  public AbstractAnimeClient(String url) {
    this(url, null);
  }

  public AbstractAnimeClient(String url, String driverProperty) {
    String property = driverProperty == null || driverProperty.isEmpty() ? "" : DriverConfig.getProperty(driverProperty);
    try {
      webDriver = DriverConfig.getDriver(property);
    } catch (NotFoundException nfE) {
      webDriver = DriverConfig.getDriver("firefox");
    }
    webDriver.get(url);
    waiter = new WebDriverWait(webDriver, 60 * 25);
  }

  /*записывает AnimeInfo в файл*/
  protected abstract void saveWatchedState(String filename);


  @Override
  public void close() throws IOException {
    webDriver.quit();
  }
}
