package org.anime.fxcomponent;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.anime.model.SavePoint;

/**
 * @author Karimov Evgeniy
 * 27.04.2022
 */
public class FxComponentFactory {
  public static VBox createSavePoint(ImageView icon, SavePoint savePoint){
    return new VBox(new FxSavePoint(icon, savePoint), new Separator(Orientation.HORIZONTAL));
  }
}
