package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

public interface AnimeInterface {
  WebClient getClient();
  List<WebElement> getQualityContainer();
  WebElement getPlayButton();
  Optional<WebElement> nextSeries();
  SavePoint getInfoAboutSeries();
  void skipOpening();
  void skipEnding();
}
