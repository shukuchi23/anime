package org.anime.web;

import org.anime.exception.NoSuchSeriesException;
import org.anime.model.SavePoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Karimov Evgeniy
 * 13.05.2022
 */

@Component
public class JutsuPlayer implements NativeSkippable, JutsuInterface {
  private final WebClient client;

  public JutsuPlayer(WebClient client) {
    this.client = client;
  }

  @Override
  public WebClient getClient() {
    return client;
  }

  @Override
  public List<WebElement> getQualityContainer() {
    // todo: нужно пофиксить
    return client.waiter.until(ExpectedConditions.elementToBeClickable(JutsuInterface.QUALITY_CONTAINER_SELECTOR))
        .findElements(JutsuInterface.QUALITY_CONTAINER_SELECTOR);
  }

  @Override
  public WebElement getPlayButton() {
    return client.waiter.until(ExpectedConditions.elementToBeClickable(JutsuInterface.PLAYER_SELECTOR));
  }

  @Override
  public Optional<WebElement> nextSeries() {
    return Optional.ofNullable(client.webDriver.findElement(JutsuInterface.EXIST_NEXT_SERIES_SELECTOR));
  }

  @Override
  public SavePoint getInfoAboutSeries() {
    final WebDriver webDriver = client.webDriver;
    final SavePoint savePoint = new SavePoint();
    savePoint.setDubName("AniDub");
    final List<WebElement> elements = webDriver.findElements(By.cssSelector("span[itemprop='name']"));
    final String series = elements.get(3).getText().split(" ")[0];
    savePoint.setTitleName(elements.get(2).getText());
    savePoint.setSeriesNum(Integer.parseInt(series));
    savePoint.setVideoUri(webDriver.getCurrentUrl());
    final SavePoint.MyDuration myDuration =
        new SavePoint.MyDuration(webDriver.findElement(By.cssSelector(".vjs-current-time-display")).getText());
    savePoint.setSeriesDuration(myDuration);
    return savePoint;
  }

  @Override
  public void skipOpening() {
    client.waiter.until(ExpectedConditions.visibilityOfElementLocated(JutsuInterface.SKIP_OPENING_BUTTON))
        .click();
  }

  @Override
  public void skipEnding() {
    client.waiter.until(ExpectedConditions.elementToBeClickable(JutsuInterface.SKIP_ENDING_BUTTON)).click();
  }
}
