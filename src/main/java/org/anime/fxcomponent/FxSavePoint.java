package org.anime.fxcomponent;

import javafx.geometry.Orientation;
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
public class FxSavePoint extends HBox implements Cloneable{
  private ImageView titleIcon;
  private SavePoint savePoint;

  public FxSavePoint(ImageView titleIcon, SavePoint savePoint) {
    this.titleIcon = titleIcon;
    this.savePoint = savePoint;
    final Separator separator = new Separator(Orientation.VERTICAL);
    final Label titleName = new Label(savePoint.getTitleName() + " (" + savePoint.getDubName() + ")");
    final Label seriesNum = new Label(savePoint.getSeriesNum() + " серия");
    final Label seriesDuration = new Label(savePoint.getSeriesDuration().toString());
    getChildren().addAll(titleIcon, separator, new VBox(titleName, seriesNum));
    setOnMouseClicked(e -> {
      switch (e.getButton()){
        case PRIMARY:
          System.out.println("Clicked Left");
          break;
        case SECONDARY:
          System.out.println("Clicked Right");
          break;
      }
    });
  }

  @Override
  public FxSavePoint clone() {
    try {
      FxSavePoint clone = (FxSavePoint) super.clone();
      // TODO: copy mutable state here, so the clone can't change the internals of the original
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
