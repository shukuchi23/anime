package org.anime.web;

import org.openqa.selenium.WebDriver;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public class StorageWorkerFactory {
  public static CookieWorker getCookieWorker(WebDriver driver){
    return new CookieWorker(driver);
  }
}
