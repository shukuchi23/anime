package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public interface AnimeInterface {
//  WebElement getNext();
//  boolean existNext();
  WebElement getQualityContainer();
  WebElement getPlayButton();
  Optional<WebElement> nextSeries();
  SavePoint getInfoAboutSeries();
  void skipOpening();
  void skipEnding();
}
