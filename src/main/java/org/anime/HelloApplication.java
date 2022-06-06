package org.anime;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.anime.config.AppConfig;
import org.anime.config.DriverConfig;
import org.anime.config.FXMLLoaderFactory;
import org.anime.config.JsonConfig;
import org.anime.controller.ExplorerController;
import org.anime.fxcomponent.FxSavePoint;
import org.anime.service.FxSavePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Lazy
@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {AppConfig.class, DriverConfig.class, JsonConfig.class, FXMLLoaderFactory.class})
@Profile("prod")
public class HelloApplication extends AbstractJavaFxApplicationSupport {

  @Autowired
  private Scene explorerScene;
  @Autowired
  private Scene creatorScene;
//
  @Autowired
  private ExplorerController controller;


  // VM option
  // --module-path "path_to_javaFx/lib" --add-modules javafx.controls,javafx.fxml


  @Override
  public void start(Stage stage) throws IOException {
    /*final ObjectMapper objectMapper = new ObjectMapper();
    SavePoint narutoSavePoint = new SavePoint(
        "Naruto",
        10,
        new SavePoint.MyDuration(11, 11),
        "AniDub",
        "https://jut.su/naruto/episode-1.html"
    );
    final File resultFile = new File("json/test.json");
    objectMapper.writeValue(resultFile, narutoSavePoint);*/

//    final Pane exploreStage = stageFactory.getExploreStage();
//    testInit(exploreStage);
//    final Scene scene1 = new Scene(exploreStage);
//
//
//    Stage explorer = stageFactory.getExplorer();
//    stage.setScene(stageFactory.getSceneByFxmlName(FxStageFactory.FXML_EXPLORER_STAGE));
//    stage.show();

    /*
    VBox root = (VBox) explorerScene.getRoot();
    long count = root.getChildren()
            .stream()
            .filter(n -> n instanceof FxSavePoint)
            .count();
    stage.setScene(count == 0 ? creatorScene : explorerScene);*/
    stage.show();

  }

  @Autowired
  @PostConstruct
  private void init(FxSavePointService service){
    List<FxSavePoint> fxSavePoints = null;
    try {
      fxSavePoints = service.savePoints();
    } catch (IOException e) {
      e.printStackTrace();
    }
    VBox root = (VBox) explorerScene.getRoot();
    root.getChildren().addAll(fxSavePoints);
  }

  public static void main(String[] args) {
    launchApp(HelloApplication.class, args);
  }

}
