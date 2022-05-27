package org.anime.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalTime;
import java.util.List;

public interface JutsuInterface extends AnimeInterface, NativeSkippable {
  By PLAYER_SELECTOR = By.id("my-player");
  By EXIST_NEXT_SERIES_SELECTOR = By.cssSelector("a.there_is_link_to_next_episode");
  By FULLSCREEN_BUTTON_SELECTOR = By.cssSelector("button.vjs-fullscreen-control.vjs-control.vjs-button");
  By WATCHED_SERIES_TIME_SELECTOR = By.cssSelector("span.vjs-current-time-display");
  By SERIES_TIME_SELECTOR = By.cssSelector("span.vjs-duration-display");
  By QUALITY_CONTAINER_SELECTOR = By.cssSelector("li.vjs-menu-item");
  By SKIP_OPENING_BUTTON =  By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-left.vjs-overlay-skip-intro.vjs-overlay-background");
  By SKIP_ENDING_BUTTON =  By.cssSelector("[title='Перейти к следующему эпизоду']");
}
