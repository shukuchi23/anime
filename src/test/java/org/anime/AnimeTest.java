package org.anime;

import org.anime.config.DriverConfig;
import org.anime.model.SavePoint;
import org.anime.repository.JsonSavePointRepository;
import org.anime.repository.SavePointRepository;
import org.anime.web.AnimeClient;
import org.anime.web.AnimeInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.anime.web.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class AnimeTest {
  @Autowired
  private AnimeInterface jutsuPlayer;
  @Autowired
  private JsonSavePointRepository savePointRepository;

  private AnimeClient animeClient;



  @Test
  public void oneSeriesWatch(){
    animeClient = new AnimeClient(savePointRepository, jutsuPlayer, 20);
   /* SavePoint testSp = new SavePoint("Самурай Чамплу",
        25,
        new SavePoint.MyDuration(2,0),
        null,
        "https://jut.su/samurai-champlo/episode-25.html");*/
    try {
      final SavePoint savePoint = savePointRepository
          .findOne("Самурай Чамплу")
          .orElse(new SavePoint(
              "Самурай Чамплу",
              25,
              new SavePoint.MyDuration(2, 0),
              null,
              "https://jut.su/samurai-champlo/episode-25.html"));
      animeClient.watch(savePoint);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @After
  public void afterClass() {
    /*if (animeClient != null)
      animeClient.stopTimer();*/
  }

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
