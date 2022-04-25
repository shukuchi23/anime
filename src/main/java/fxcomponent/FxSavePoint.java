package fxcomponent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.SavePoint;

/**
 * @author Karimov Evgeniy
 * 25.04.2022
 */
public class FxSavePoint extends HBox {
  private ImageView titleIcon;
  private Separator separator;
  private TextArea titleInfo;

  public FxSavePoint(ImageView titleIcon, SavePoint savePoint) {
    this.titleIcon = titleIcon;
    titleInfo = new TextArea(String.format(
        "%s (%s)\n%d серия",
        savePoint.getTitleName(), savePoint.getDubName(),
        savePoint.getSeriesNum()
    ));
  }
}
