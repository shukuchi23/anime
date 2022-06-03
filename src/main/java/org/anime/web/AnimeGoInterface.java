package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class AnimeGoInterface implements AnimeInterface {
  @Override
  public WebClient getClient() {
    return null;
  }

  @Override
  public List<WebElement> getQualityContainer() {
    return null;
  }

  @Override
  public WebElement getFullScreenButton() {
    return null;
  }

  @Override
  public WebElement getPlayButton() {
    return null;
  }

  @Override
//  "td.fp-timeline-wrapper"
  public WebElement getTimeLineBar() {
    return null;
  }

  @Override
  public Optional<WebElement> nextSeries() {
    return Optional.empty();
  }

  @Override
  public SavePoint getInfoAboutSeries(@Nullable SavePoint oldSavePoint) {
    return null;
  }

  @Override
  public WebElement getSkipOpeningButtonWhenVisible() {
    return null;
  }

  @Override
  public WebElement getSkipEndingButtonWhenVisible() {
    return null;
  }

  @Override
  public void skipOpening() {

  }

  @Override
  public void skipEnding() {

  }

  @Override
  public boolean startWithTime(SavePoint.MyDuration seriesDuration) {
    return false;
  }

  @Override
  public boolean isLastEpisode() {
    return false;
  }
}
