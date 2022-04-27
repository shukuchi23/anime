package org.anime.fxcomponent;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.anime.model.SavePoint;

/**
 * @author Karimov Evgeniy
 * 25.04.2022
 */
public class FxSavePoint extends HBox implements Cloneable{
  private ImageView titleIcon;
  private Separator separator;
//  private TextArea titleInfo;
  private Label titleName;
  private Label seriesNum;
  private Label seriesDuration;
  private Label dubName;
  private Label updateTime;
  private String sourceURI; // https://jut.su/

  public FxSavePoint(ImageView titleIcon, SavePoint savePoint) {
    this.titleIcon = titleIcon;
    separator = new Separator(Orientation.VERTICAL);
    titleName = new Label(savePoint.getTitleName());
    seriesNum = new Label(savePoint.getSeriesNum() + " серия");
    seriesDuration = new Label(savePoint.getSeriesDuration().toString());
    dubName = new Label();
    getChildren().addAll(titleName, separator);
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
