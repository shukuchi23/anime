package org.anime.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.anime.HelloApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FXMLLoaderFactory {
    public static final String FXML_EXPLORER_STAGE = "saveStage";
    public static final String FXML_CREATOR_STAGE = "hello-view";

    private InputStream getSceneByName(String sceneName) {
        if (sceneName.lastIndexOf(".fxml") == -1)
            sceneName += ".fxml";
        return HelloApplication.class.getResourceAsStream("/fxml/" + sceneName);
    }

    @Bean
    public Scene explorerScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try(InputStream stream = getSceneByName(FXML_EXPLORER_STAGE)){
            return new Scene(fxmlLoader.load(stream));
        }
    }

    @Bean
    public Scene creatorScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try(InputStream stream = getSceneByName(FXML_CREATOR_STAGE)){
            return new Scene(fxmlLoader.load(stream));
        }
    }
}
