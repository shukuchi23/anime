package org.anime;

import org.anime.config.AppConfig;
import org.anime.config.DriverConfig;
import org.anime.config.H2Config;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.anime.fxcomponent.FxComponentFactory;
import org.anime.fxcomponent.FxSavePoint;
import org.anime.model.SavePoint;
import org.anime.utils.IconProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Objects;

@Lazy
@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {AppConfig.class, DriverConfig.class, H2Config.class})
public class HelloApplication extends AbstractJavaFxApplicationSupport {
  // VM option
  // --module-path "/opt/jdk/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml
  @Override
  public void start(Stage stage) throws IOException {
    try (InputStream resourceAsStream = getClass().getResourceAsStream("/fxml/saveStage.fxml")) {
      FXMLLoader fxmlLoader = new FXMLLoader();
      VBox root = fxmlLoader.load(resourceAsStream);
      // test_block
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

      final VBox naruto = FxComponentFactory.createSavePoint(
          new ImageView(IconProvider.getImageByName("jutsu.png")),
          bleachSavePoint);
      final VBox bleach = FxComponentFactory.createSavePoint(
          new ImageView(IconProvider.getImageByName("animego.png")),
          bleachSavePoint);
      root.getChildren().addAll(naruto, bleach);
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


}
