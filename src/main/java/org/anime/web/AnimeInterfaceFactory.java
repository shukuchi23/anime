package org.anime.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * @author Karimov Evgeniy
 * 10.06.2022
 */
public class AnimeInterfaceFactory {
  public static AnimeInterface getInterfaceByUrl(WebDriver driver, String url){
    return null;
    // todo придумай что-нибудь
    /*if (url.contains("jut.su"))
      return new JutsuPlayer(driver)
    if (url.contains("animego"))
      return "animego.png";*/
  }
  public static AnimeInterface getInterfaceByName(WebDriver driver, String name){
    return null;
    // todo чего ты ждешь?
  }
}
