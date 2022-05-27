package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
//    "button.vjs-menu-button.vjs-menu-button-popup.vjs-button[title='Выбрать качество']"
    client.waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
        "button.vjs-menu-button.vjs-menu-button-popup.vjs-button[title='Выбрать качество']"))).click();
    return client.webDriver.findElements(JutsuInterface.QUALITY_CONTAINER_SELECTOR);
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
  public WebElement getFullScreenButton() {
    return client.waiter.until(ExpectedConditions.elementToBeClickable(FULLSCREEN_BUTTON_SELECTOR));
//    return client.webDriver.findElement(FULLSCREEN_BUTTON_SELECTOR);
  }

  @Override
  public void startWithTime(SavePoint.MyDuration seriesDuration) {
    final WebElement progressBar = client.waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.vjs-progress-control.vjs-control")));
    // todo ты тупая сука лил бич эй ступид
    final Actions action = new Actions(client.webDriver);
    final int width = progressBar.getSize().getWidth();
    int beginNum = -(width / 2);
    getPlayButton().click();
    int K = 9;
    action.moveToElement(progressBar, beginNum + seriesDuration.toSecond() + K, 1).click().perform();
    System.out.println();
  }

  @Override
  public SavePoint getInfoAboutSeries(@Nullable SavePoint oldSavePoint) {
    final WebDriver webDriver = client.webDriver;

    if (oldSavePoint == null){
      final WebElement element = webDriver.findElement(By.cssSelector("h1.header_video>span"));
      String[] titleAndSeries = extractNameAndSeries(element.getText());
      oldSavePoint = new SavePoint(
          titleAndSeries[0],
          Integer.parseInt(titleAndSeries[1]),
          null,
          "AniDub",
          webDriver.getCurrentUrl()
          );
      /*
      savePoint.setDubName("AniDub");

      savePoint.setTitleName(titleAndSeries[0]);
      savePoint.setSeriesNum(Integer.parseInt(titleAndSeries[1]));
      savePoint.setVideoUri(webDriver.getCurrentUrl());*/
    }

    String duration = client.getElementInnerText(".vjs-current-time-display");
//    String duration = client.executeJsScript("return $('.vjs-current-time-display')[0].innerHTML");
    final SavePoint.MyDuration myDuration =
        new SavePoint.MyDuration(duration);
    oldSavePoint.setSeriesDuration(myDuration);
    return oldSavePoint;
  }

  private static String[] extractNameAndSeries(String text) {
    String s = text.toLowerCase(Locale.ROOT);
    int frstDigInd = getFirstDigitByTailOnString(s);
    return new String[]{text.substring(0, frstDigInd - 1), text.substring(frstDigInd, text.lastIndexOf(' '))};
  }

  private static int getFirstDigitByTailOnString(String txt) {
    char[] chars = txt.toCharArray();
    int rez = -1;
    for (int i = txt.length() - 1; i != 0; i--) {
      if (chars[i] == ' ' && rez != -1)
        return rez;
      if (Character.isDigit(chars[i]))
        rez = i;
    }
    return -1;
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
