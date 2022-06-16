package org.anime.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.anime.HelloApplication;
import org.anime.service.FxSavePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration("factory")
public class FXMLLoaderFactory {
    public static final String FXML_EXPLORER_STAGE = "saveStage";
    public static final String FXML_CREATOR_STAGE = "hello-view";

    @Autowired
    public FxSavePointService fxSavePointService;

    private InputStream getSceneByName(String sceneName) {
        if (sceneName.lastIndexOf(".fxml") == -1)
            sceneName += ".fxml";
        return HelloApplication.class.getResourceAsStream("/fxml/" + sceneName);
    }

    @Bean("explorerScene")
    public Scene explorerScene()  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try(InputStream stream = getSceneByName(FXML_EXPLORER_STAGE)){
            Scene scene = new Scene(fxmlLoader.load(stream));
            VBox root = (VBox) scene.getRoot();
            root.getChildren().addAll(fxSavePointService.savePoints());
            return scene;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean("creatorScene")
    public Scene creatorScene(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try(InputStream stream = getSceneByName(FXML_CREATOR_STAGE)){
            return new Scene(fxmlLoader.load(stream));
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
