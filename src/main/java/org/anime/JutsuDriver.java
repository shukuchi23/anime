package org.anime;

import org.anime.model.SavePoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.anime.web.AbstractAnimePlayer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JutsuDriver {
  private static final By PLAYER_SELECTOR = By.id("my-player");
  private static final By EXIST_NEXT_SERIES_SELECTOR = By.cssSelector("a.there_is_link_to_next_episode");
  private static final By WATCHED_SERIES_TIME_SELECTOR = By.cssSelector("span.vjs-current-time-display");
  private static final By SERIES_TIME_SELECTOR = By.cssSelector("span.vjs-duration-display");
  private static final By QUALITY_CONTAINER_SELECTOR = By.cssSelector("li.vjs-menu-item");
//  private static final String EXIST_NEXT_SERIES = "a.there_is_link_to_next_episode";
//  private static final String EXIST_NEXT_SERIES = "a.there_is_link_to_next_episode";
//  private static final String EXIST_NEXT_SERIES = "a.there_is_link_to_next_episode";

/*
  @Override
  public boolean hasNext() {
    try {
      WebElement element = webDriver.findElement(EXIST_NEXT_SERIES_SELECTOR);
      if (element == null)
        return false;
    } catch (Exception ignore) {
      return false;
    }
    return true;
  }

  @Override
  public WebElement next() {
    By by = By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-right.vjs-overlay-skip-intro.vjs-overlay-background");

  }*/
  /*
   *     final WebElement player = driver.findElement(By.id("my-player"));
   *
         WebElement qualitySelector = webDriverWait.until(ExpectedConditions
         .elementToBeClickable(By.cssSelector("button.vjs-menu-button.vjs-menu-button-popup.vjs-button[title='Выбрать качество']")));

         final List<WebElement> qualities = driver.findElements(By.cssSelector("li.vjs-menu-item"));
     final WebElement maxQuality = findMaxQuality(qualities);
     if (!maxQuality.isSelected()) {
       maxQuality.click();
     }

     final WebElement fullscreenButton = driver.findElement(By.cssSelector("[title='На весь экран']"));
     fullscreenButton.click();
     if (skipOpening) {
       final WebElement skipOpeningButton = webDriverWait
           .until(ExpectedConditions.elementToBeClickable(
               By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-left.vjs-overlay-skip-intro.vjs-overlay-background")));
       skipOpeningButton.click();
     }
     if (skipEnding) {
       final WebElement skipEndingButton = webDriverWait.withTimeout(Duration.of(15, ChronoUnit.MINUTES)).until(
           ExpectedConditions.elementToBeClickable(By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-right.vjs-overlay-skip-intro.vjs-overlay-background")));
       skipEndingButton.click();
     }
   *
    * */





  /*@Override
  public String getTitleName() {
    return null;
  }

  @Override
  public String getSeries() {
    return null;
  }

  @Override
  public String getWatchedSeriesTime() {
    return webDriver.findElement(WATCHED_SERIES_TIME_SELECTOR).getText();
  }

  @Override
  public String getSeriesTime() {
    return webDriver.findElement(SERIES_TIME_SELECTOR).getText();
  }*/

}
