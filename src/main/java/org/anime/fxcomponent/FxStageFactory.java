package org.anime.fxcomponent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.anime.HelloApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Karimov Evgeniy
 * 29.04.2022
 */
public class FxStageFactory {
    private FXMLLoader loader = new FXMLLoader();
    public static final String FXML_EXPLORER_STAGE = "saveStage";
    public static final String FXML_CREATOR_STAGE = "hello-view";
    private Stage explorer;
    private Stage creator;

    public Stage getCreator() throws IOException {
        if (creator == null) {
            creator = new Stage();
            creator.setScene(getSceneByFxmlName(FXML_CREATOR_STAGE));
        }
        return creator;
    }

    public Stage getExplorer() throws IOException {
        if (explorer == null) {
            explorer = new Stage();
            explorer.setScene(getSceneByFxmlName(FXML_EXPLORER_STAGE));
        }
        return explorer;
    }

    public Scene getSceneByFxmlName(String fxmlName) throws IOException {
        try (InputStream stream = getSceneByName(fxmlName)) {
            return new Scene(loader.load(stream));
        } catch (LoadException e){
            loader = new FXMLLoader();
            return getSceneByFxmlName(fxmlName);
        }
    }
    private InputStream getSceneByName(String sceneName) {
        if (sceneName.lastIndexOf(".fxml") == -1)
            sceneName += ".fxml";
        return HelloApplication.class.getResourceAsStream("/fxml/" + sceneName);
    }
}
