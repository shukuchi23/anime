package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface AnimeInterface {

  // Слушатель
  WebClient getClient();
  List<WebElement> getQualityContainer();
  WebElement getFullScreenButton();
  WebElement getPlayButton();
  Optional<WebElement> nextSeries();
  SavePoint getInfoAboutSeries(@Nullable SavePoint oldSavePoint);
  WebElement getSkipOpeningButtonWhenVisible();
  WebElement getSkipEndingButtonWhenVisible();
  void skipOpening();
  void skipEnding();
  boolean startWithTime(SavePoint.MyDuration seriesDuration);
  boolean isLastEpisode();
}
