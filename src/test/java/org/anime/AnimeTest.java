package org.anime;

import org.anime.config.DriverConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.anime.web.AnimeService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnimeTest {

  private AnimeService service;
//  private WebDriver driver;

  @Test(expected = NotFoundException.class)
  public void notFoundDriver() {
//    WebDriver yandex_driver = DriverConfig.getDriver("yandex_driver");
  }

  @Test
  public void testAllDrivers() {
//    Assert.assertNotNull(DriverConfig.getDriver("firefox"));
//    Assert.assertNotNull(DriverConfig.getDriver("chrome"));
//    Assert.assertNotNull(DriverConfig.getDriver("opera"));
  }

  @Test
  public void nextSeries() {
    String url = "https://jut.su/mobs-psycho/season-1/episode-1.html";
//    service = new AnimeService(new JutsuDriver());
//    AbstractAnimeClient driver = service.runAnime();
//    WebDriverWait webDriverWait = new WebDriverWait(driver, 60 * 25);
//    webDriverWait.until(ExpectedConditions.urlContains("season-1/episode-2.html"));

//    Assert.assertEquals(driver.getCurrentUrl(), "https://jut.su/mobs-psycho/season-1/episode-2.html");
  }

  @After
  public void closeBrowser() {
//    service.close();
  }


}
