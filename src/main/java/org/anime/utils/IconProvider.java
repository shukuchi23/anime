package org.anime.utils;

import javafx.scene.image.Image;
import org.anime.HelloApplication;
import org.openqa.selenium.NotFoundException;

import java.io.InputStream;
import java.util.Objects;

public class IconProvider {
  public static Image getImageByName(String filename){
    final InputStream resourceAsStream = HelloApplication.class.getResourceAsStream("/img/" + filename);
    return new Image(Objects.requireNonNull(resourceAsStream));
  }
  public static String iconNameFromLink(String uri){
    if (uri.contains("jut.su"))
      return "jutsu.png";
    if (uri.contains("animego"))
      return "animego.png";
    throw new NotFoundException("Не поддерживаемый адрес: " + uri);
  }

//    public static getIcon()
}
