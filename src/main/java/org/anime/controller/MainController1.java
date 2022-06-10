package org.anime.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import org.anime.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class MainController1 {

  @FXML
  private Group firstTimeGroup;

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
  public void initialize(){
    final String property = AppConfig.getProperty("myapp.default_browser");
    firstTimeGroup.visibleProperty().setValue(property == null || property.isEmpty());
    startButton.onMouseClickedProperty().set(e -> addTitle());
  }

  private void addTitle(){
    if (firstTimeGroup.isVisible()) {
      final String selectedBrowser = ((RadioButton)browsers.getSelectedToggle()).getText();
      AppConfig.setProperties("myapp.default_browser", selectedBrowser.toLowerCase());
      AppConfig.setProperties("myapp.skip_opening", String.valueOf(skipOpening.isSelected()));
      AppConfig.setProperties("myapp.skip_ending", String.valueOf(skipEnding.isSelected()));
    }

    final String text = searchPanel.getEditor().getText();

  }

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
