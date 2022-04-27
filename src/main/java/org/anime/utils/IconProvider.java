package org.anime.utils;

import javafx.scene.image.Image;
import org.anime.HelloApplication;

import java.io.InputStream;
import java.util.Objects;

public class IconProvider {
  public static Image getImageByName(String filename){
    final InputStream resourceAsStream = HelloApplication.class.getResourceAsStream("/img/" + filename);
    return new Image(Objects.requireNonNull(resourceAsStream));
  }

//    public static getIcon()
}
