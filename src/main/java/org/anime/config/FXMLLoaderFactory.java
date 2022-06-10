package org.anime.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.anime.HelloApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Profile("prod")
public class FXMLLoaderFactory {
    public static final String FXML_EXPLORER_STAGE = "saveStage";
    public static final String FXML_CREATOR_STAGE = "hello-view";

    private InputStream getSceneByName(String sceneName) {
        if (sceneName.lastIndexOf(".fxml") == -1)
            sceneName += ".fxml";
        return HelloApplication.class.getResourceAsStream("/fxml/" + sceneName);
    }

    @Bean
    public Scene explorerScene()  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try(InputStream stream = getSceneByName(FXML_EXPLORER_STAGE)){
            return new Scene(fxmlLoader.load(stream));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean
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
