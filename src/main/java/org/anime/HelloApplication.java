package org.anime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.anime.config.AppConfig;
import org.anime.config.DriverConfig;
import org.anime.config.H2Config;
import org.anime.fxcomponent.FxComponentFactory;
import org.anime.fxcomponent.FxStageFactory;
import org.anime.model.SavePoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Scanner;

@Lazy
@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {AppConfig.class, DriverConfig.class, H2Config.class})
public class HelloApplication extends AbstractJavaFxApplicationSupport {
  public static FxStageFactory stageFactory = new FxStageFactory();
  // VM option
  // --module-path "path_to_javaFx/lib" --add-modules javafx.controls,javafx.fxml
  @Override
  public void start(Stage stage) throws IOException {
//    final Pane exploreStage = stageFactory.getExploreStage();
//    testInit(exploreStage);
//    final Scene scene1 = new Scene(exploreStage);
//
//

    try (InputStream resourceAsStream = getClass().getResourceAsStream("/fxml/saveStage.fxml")) {
      FXMLLoader fxmlLoader = new FXMLLoader();
      // загрузка окна на основе файла
      //Vbox - см. https://metanit.com/java/javafx/3.4.php
      VBox root = fxmlLoader.load(resourceAsStream);
      // test_block
      testInit(root);
      // test_block_end
      Scene scene = new Scene(root);
      stage.setTitle("Hello!");
      stage.setScene(scene);

      stage.show();
    }
  }

  public static void main(String[] args) {
    launchApp(HelloApplication.class, args);
  }

  private void testInit(Pane stage) {
    SavePoint narutoSavePoint = new SavePoint(
        "Naruto",
        10,
        LocalTime.of(0, 11, 11),
        "AniDub",
        "https://jut.su/naruto/episode-1.html"
    );
    SavePoint bleachSavePoint = new SavePoint(
        "Bleach",
        228,
        LocalTime.of(0, 14, 11),
        "Subtitles",
        "https://jut.su/naruto/episode-1.html"
    );

    final VBox naruto = FxComponentFactory.createSavePoint("jutsu.png", narutoSavePoint);
    final VBox bleach = FxComponentFactory.createSavePoint("animego.png", bleachSavePoint);
    stage.getChildren().addAll(naruto, bleach);
  }
}
