package org.anime.web;

import org.openqa.selenium.WebDriver;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public abstract class StorageWorker {
  protected WebDriver driver;
  public StorageWorker(WebDriver driver){
    this.driver = driver;
  }
  protected abstract void reloadEntity(Object entity);
}
