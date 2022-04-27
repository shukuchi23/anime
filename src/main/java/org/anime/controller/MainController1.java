package org.anime.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController1 {


//  private AnimeService animeService;
  @FXML
  private ToggleGroup browsers;

  @FXML
  private Button startButton;

  @FXML
  private CheckBox skipOpening;

  @FXML
  private CheckBox skipEnding;

  @FXML
  private ComboBox<String> searchPanel;

  @FXML
  void clickStart(ActionEvent event) {
    Toggle selectedToggle = browsers.getSelectedToggle();
    String text = ((RadioButton) selectedToggle).getText().toLowerCase();
    String url = searchPanel.getEditor().getText();
//    animeService = new AnimeService(url, text);
//    WebDriver driver = animeService.runAnime(skipOpening.isSelected(), skipEnding.isSelected());
//    while (driver != null) ;

  }

}
