package org.anime.utils;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * @author Karimov Evgeniy
 * 10.06.2022
 */
public class SavePointHelper {

  public static SavePoint tryToExtractSavePointFromUrl(String url){
    WebDriver driver = new HtmlUnitDriver(true);
    driver.get(url);

  }
}
