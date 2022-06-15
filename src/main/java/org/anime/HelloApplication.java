package org.anime;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.anime.config.FXMLLoaderFactory;
import org.anime.controller.ExplorerController;
import org.anime.fxcomponent.FxSavePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@SpringBootApplication
@ConfigurationPropertiesScan( basePackages = {"org.anime.config"})
@Profile("prod")
public class HelloApplication extends AbstractJavaFxApplicationSupport {

//
  /*@Autowired
  @Qualifier("explorerScene")*/
  @Autowired
  private Scene explorerScene;

  @Autowired
  private FXMLLoaderFactory factory;

 /* @Autowired
  @Qualifier("creatorScene")*/
  @Autowired
  private Scene creatorScene;
  //
 /* @Autowired
  @Qualifier("explorerController")*/
  @Autowired
  private ExplorerController explorerController;

  public Scene getExplorerScene() {
    return explorerScene;
  }

  public FXMLLoaderFactory getFactory() {
    return factory;
  }

  public void setFactory(FXMLLoaderFactory factory) {
    this.factory = factory;
  }

  public Scene getCreatorScene() {
    return creatorScene;
  }

  public ExplorerController getExplorerController() {
    return explorerController;
  }

  /*public HelloApplication() {
  }*/

  @Autowired
  public void setExplorerScene(@Qualifier("explorerScene") Scene explorerScene) {
    this.explorerScene = explorerScene;
  }

  @Autowired
  public void setCreatorScene(@Qualifier("creatorScene") Scene creatorScene) {
    this.creatorScene = creatorScene;
  }

  public void setExplorerController(ExplorerController explorerController) {
    this.explorerController = explorerController;
  }
// VM option
  // --module-path "path_to_javaFx/lib" --add-modules javafx.controls,javafx.fxml


  @Override
  public void start(Stage stage) throws IOException {
    /*explorerScene =  factory.explorerScene();
    creatorScene = factory.creatorScene();*/
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


    VBox root = (VBox) explorerScene.getRoot();
    long count = root.getChildren()
        .stream()
        .filter(n -> n instanceof FxSavePoint)
        .count();
    stage.setScene(count == 0 ? creatorScene : explorerScene);
    stage.show();

  }

  public static void main(String[] args) {
    launchApp(HelloApplication.class, args);

  }

}
