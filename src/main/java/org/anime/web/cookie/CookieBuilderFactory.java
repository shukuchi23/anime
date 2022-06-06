package org.anime.web.cookie;

/**
 * @author Karimov Evgeniy
 * 06.06.2022
 */
public class CookieBuilderFactory {
  public static JutSuCookieBuilder jutSuCookieBuilder(){
    return new JutSuCookieBuilder("player[currenttime]");
  }
  public static AnimeGoCookieBuilder animeGoCookieBuilder(String name){
    return new AnimeGoCookieBuilder("seria-progress");
  }
}
