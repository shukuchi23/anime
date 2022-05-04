package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebElement;

public interface AnimeInterface extends AutoCloseable {
//  WebElement getNext();
//  boolean existNext();
  WebElement getQualityContainer();
  WebElement getPlayButton();
  WebElement nextSeries();
  SavePoint getInfoAboutSeries();

}
