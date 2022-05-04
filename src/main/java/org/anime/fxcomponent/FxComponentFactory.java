package org.anime.fxcomponent;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.anime.model.SavePoint;
import org.anime.utils.IconProvider;

/**
 * @author Karimov Evgeniy
 * 27.04.2022
 *
 * класс для получения JavaFX объектов, используемых приложением
 */
public class FxComponentFactory {

  private static final double DEFAULT_IMAGE_HEIGHT = 76;
  private static final double DEFAULT_IMAGE_WIDTH = 76;

  // создает запись в графическом интерфейсе вида
  // =======================================
  //       | название_сериала ( озвучка )
  // ICON  | серия
  //       |
  // =======================================
  // * imageName - имя картинки, расположенной в папке проекта по пути resource/img
  // * savePoint - информация об сериале
  public static VBox createSavePointVBox(String imageName, SavePoint savePoint){
    FxSavePoint fxSavePoint = createFxSavePoint(imageName, savePoint);
    return createSavePointVBox(fxSavePoint);
  }

  private static VBox createSavePointVBox(FxSavePoint fxSavePoint){
    return new VBox(fxSavePoint, new Separator(Orientation.HORIZONTAL));
  }

  public static FxSavePoint createFxSavePoint(String imageName, SavePoint savePoint){
    final ImageView imageView = new ImageView(IconProvider.getImageByName(imageName));
    imageView.setFitHeight(DEFAULT_IMAGE_HEIGHT);
    imageView.setFitWidth(DEFAULT_IMAGE_WIDTH);
    return new FxSavePoint(imageView, savePoint);
  }

}
