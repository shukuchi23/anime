package org.anime.web;

import org.openqa.selenium.WebElement;

import java.util.List;

public class AnimeService {
  private AbstractAnimePlayer driver;

  public AnimeService(AbstractAnimePlayer driver){
    this.driver = driver;
  }
//  final WebDriverWait webDriverWait = new WebDriverWait(driver, 60 * 25);

//  public AnimeService(String url) {
//    this(url, null);
//  }
//
//  public AnimeService(String url, String driverProperty) {
//    this.url = url;
//    String property = driverProperty == null || driverProperty.isEmpty() ? "" : DriverConfig.getProperty(driverProperty);
//    try {
//      driver = DriverConfig.getDriver(property);
//    } catch (NotFoundException nfE) {
//      driver = DriverConfig.getDriver("firefox");
//    }
//    driver.get(url);
//  }


//  public AbstractAnimeClient runAnime() {
//    return runAnime(true, true);
//  }

//  public AbstractAnimeClient runAnime(boolean skipOpening, boolean skipEnding) {
//    WebElement player = driver.getPlayer();
//
//    // player.click()
//
//    final WebElement qualitySelector = webDriverWait.until(ExpectedConditions
//        .elementToBeClickable(By.cssSelector("button.vjs-menu-button.vjs-menu-button-popup.vjs-button[title='Выбрать качество']")));
//    qualitySelector.click();
//
//    final List<WebElement> qualities = driver.findElements(By.cssSelector("li.vjs-menu-item"));
//    final WebElement maxQuality = findMaxQuality(qualities);
//    if (!maxQuality.isSelected()) {
//      maxQuality.click();
//    }
//
//    final WebElement fullscreenButton = driver.findElement(By.cssSelector("[title='На весь экран']"));
//    fullscreenButton.click();
//    if (skipOpening) {
//      final WebElement skipOpeningButton = webDriverWait
//          .until(ExpectedConditions.elementToBeClickable(
//              By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-left.vjs-overlay-skip-intro.vjs-overlay-background")));
//      skipOpeningButton.click();
//    }
//    if (skipEnding) {
//      final WebElement skipEndingButton = webDriverWait.withTimeout(Duration.of(15, ChronoUnit.MINUTES)).until(
//          ExpectedConditions.elementToBeClickable(By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-right.vjs-overlay-skip-intro.vjs-overlay-background")));
//      skipEndingButton.click();
//    }
//    return driver;
//  }
//
//  public boolean existNext() {
//    try {
//      final WebElement skipEndingButton = webDriverWait.withTimeout(Duration.of(15, ChronoUnit.MINUTES)).until(
//          ExpectedConditions.elementToBeClickable(By.cssSelector("div.vjs-overlay.vjs-overlay-bottom-right.vjs-overlay-skip-intro.vjs-overlay-background")));
//    } catch (Exception e) {
//      return false;
//    }
//    return true;
//  }


  private WebElement findMaxQuality(List<WebElement> qualityContainer) {
    return qualityContainer.stream()
        .filter(element -> element.getText().matches("\\d+p")).max((a, b) -> {
          Integer quality = Integer.parseInt(a.getText().replace("p", ""));
          Integer quality2 = Integer.parseInt(b.getText().replace("p", ""));
          return quality.compareTo(quality2);
        })
        .orElseThrow(() -> new RuntimeException("Не удалось найти лучшее качество бро"));
  }
}