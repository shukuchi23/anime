package org.anime.fxcomponent;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.anime.model.SavePoint;

/**
 * @author Karimov Evgeniy
 * 25.04.2022
 */
public class FxSavePoint extends HBox{
  private ImageView titleIcon;
  private SavePoint savePoint;

  public FxSavePoint(ImageView titleIcon, SavePoint savePoint) {
    this.titleIcon = titleIcon;
    this.savePoint = savePoint;
    final Separator separator = new Separator(Orientation.VERTICAL);
    final Label titleName = new Label(savePoint.getTitleName() + " (" + savePoint.getDubName() + ")");
    final Label seriesNum = new Label(savePoint.getSeriesNum() + " серия");
//    final Label seriesDuration = new Label(savePoint.getSeriesDuration().toString());
    getChildren().addAll(titleIcon, separator, new VBox(titleName, seriesNum));
    setOnMouseClicked(e -> {

      switch (e.getButton()){
        case PRIMARY:

          System.out.printf("[%s] Clicked Left\n", savePoint.getTitleName());
          break;
        case SECONDARY:
          System.out.printf("[%s] Clicked Right\n", savePoint.getTitleName());
          break;
      }
    });
  }

  public ImageView getTitleIcon() {
    return titleIcon;
  }

  public void setTitleIcon(ImageView titleIcon) {
    this.titleIcon = titleIcon;
  }

  public SavePoint getSavePoint() {
    return savePoint;
  }

  public void setSavePoint(SavePoint savePoint) {
    this.savePoint = savePoint;
  }
}
