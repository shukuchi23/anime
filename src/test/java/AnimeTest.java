import config.DriverConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.AbstractAnimeClient;
import service.AnimeService;

public class AnimeTest {
  private AnimeService service;
//  private WebDriver driver;

  @Test(expected = NotFoundException.class)
  public void notFoundDriver() {
    WebDriver yandex_driver = DriverConfig.getDriver("yandex_driver");
  }

  @Test()
  public void testAllDrivers() {
//    Assert.assertNotNull(DriverConfig.getDriver("firefox_driver"));
    Assert.assertNotNull(DriverConfig.getDriver("chrome"));
    Assert.assertNotNull(DriverConfig.getDriver("opera"));
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