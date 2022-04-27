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
import org.anime.fxcomponent.FxSavePoint;
import org.anime.model.SavePoint;
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
//      Pane panel = (Pane) fxmlLoader.load(resourceAsStream);
//      fxmlLoader.setLocation(url);

//    InputStream inputStream = HelloApplication.class.getResource("/hello-view.fxml").openStream();
      VBox root = fxmlLoader.load(resourceAsStream);
      SavePoint savePoint = new SavePoint(
          "Naruto",
          10,
          LocalTime.of(0, 11, 11),
          "AniDub",
          "https://jut.su/naruto/episode-1.html"
      );

      root.getChildren().add(new FxSavePoint(
          new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/jutsu.png")))),
          savePoint));
//      final ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/jutsu.png"))));
//      Parent root = new VBox(imageView);
//      new VBox()
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
