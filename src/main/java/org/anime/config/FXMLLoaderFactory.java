package org.anime.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.anime.HelloApplication;
import org.anime.service.FxSavePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Profile("prod")
@Configuration("factory")
public class FXMLLoaderFactory {
    public static final String FXML_EXPLORER_STAGE = "save-stage";
    public static final String FXML_CREATOR_STAGE = "hello-view";

    @Autowired
    private FxSavePointService fxSavePointService;

    private InputStream getSceneByName(String sceneName) {
        if (sceneName.lastIndexOf(".fxml") == -1)
            sceneName += ".fxml";
        return HelloApplication.class.getResourceAsStream("/fxml/" + sceneName);
    }

    @Bean("explorerScene")
    @Lazy
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
    @Lazy
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
