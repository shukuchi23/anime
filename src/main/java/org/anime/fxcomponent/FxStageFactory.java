package org.anime.fxcomponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.anime.HelloApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Karimov Evgeniy
 * 29.04.2022
 */
public class FxStageFactory {
  private final FXMLLoader loader = new FXMLLoader();
  private Stage explorer;
  private Stage creator;

  // todo:

  public Pane getSavePointBuilderStage() {
    return null;
  }

  public void showExplorer() throws IOException {
    if (explorer == null) {
      explorer = new Stage();
      explorer.setScene(getExploreScene());
    }
    explorer.show();
  }
  // TODO: рефактор
  public void showCreator() throws IOException {
    if (creator == null) {
      creator = new Stage();
      creator.setScene(getCreatorScene());
    }
    creator.show();
  }

  public Scene getExploreScene() throws IOException {
    try (InputStream resourceAsStream = HelloApplication.class.getResourceAsStream("/fxml/saveStage.fxml")) {
      // загрузка окна на основе файла
      //Vbox - см. https://metanit.com/java/javafx/3.4.php
      VBox root = loader.load(resourceAsStream);
      Scene scene = new Scene(root);
      return scene;
    }

  }
  public Scene getCreatorScene() throws IOException {
//    try (InputStream resourceAsStream = HelloApplication.class.getResourceAsStream("/fxml/hello-view.fxml")) {
    try (InputStream resourceAsStream = getSceneByName("hello-view.fxml")) {
      // загрузка окна на основе файла
      //Vbox - см. https://metanit.com/java/javafx/3.4.php
      VBox root = loader.load(resourceAsStream);
      Scene scene = new Scene(root);
      return scene;
    }
  }
  private InputStream getSceneByName(String sceneName){
    return HelloApplication.class.getResourceAsStream("/fxml/" + sceneName);
  }
}
