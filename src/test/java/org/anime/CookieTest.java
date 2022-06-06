package org.anime;

import org.anime.config.DriverConfig;
import org.anime.web.StorageWorkerFactory;
import org.anime.web.WebClient;
import org.anime.web.cookie.CookieBuilderFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public class CookieTest {

  @Test
  public void buildAnimeGoCookie() {
//    final AnimeGoCookieBuilder animeGoCookieBuilder = CookieBuilderFactory.animeGoCookieBuilder();

  }

  @Test
  public void insertCookie() {
    WebClient client = new WebClient(new DriverConfig().chromeOption());
    Cookie cookie = new Cookie("player[currenttime]",
        "[.25142|0.!.25177|0.!.25209|1410.!.25244|0.!.4424|333.!.4389|310.!.26047|105.]"
    );
    StorageWorkerFactory
        .getCookieWorker(client.getWebDriver())
        .reloadEntity(cookie);
    // todo: проверить выше
  }

  @Test
  public void buildJutSuCookie() {
    // player[currenttime]:"[.25142|0.!.25177|0.!.25209|1410.!.25244|0.!.4424|333.!.4389|310.!.26047|105.]"
    final Cookie cookie = new Cookie("player[currenttime]",
        "[.25142|0.!.25177|0.!.25209|1410.!.25244|0.!.4424|333.!.4389|310.!.26047|105.]"
    );

    final Cookie build = CookieBuilderFactory.jutSuCookieBuilder()
        .addValue("25142", 0)
        .addValue("25177", 0)
        .addValue("25209", 1410)
        .addValue("25244", 0)
        .addValue("4424", 333)
        .addValue("4389", 310)
        .addValue("26047", 105)
        .build();

    Assert.assertEquals(cookie, build);
  }
}
