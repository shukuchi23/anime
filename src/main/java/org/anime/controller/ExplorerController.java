package org.anime.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import org.anime.HelloApplication;
import org.anime.fxcomponent.FxSavePoint;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.anime.utils.StringUtils.log;

@Component
@Profile({"prod", "test"})
public class ExplorerController {


    @FXML
    private MenuItem AboutMenuItem;

    @FXML
    private MenuItem CloseMenuItem;

    @FXML
    private MenuItem ImportMenuItem;

    @FXML
    private MenuItem SaveAsMenuItem;

    @FXML
    private MenuItem addNewMenuItem;

    @FXML
    private Menu editMenu;

    @FXML
    private Menu fileMenu;

    @FXML
    private VBox explorerVBox;

    @FXML
    private Menu helpMenu;

    @FXML
    private List<FxSavePoint> savePoints;


    private HelloApplication application;

    public void setApplication(HelloApplication application){
        log("fxml", "set application");
        this.application = application;
    }

    @FXML
    void initialize() {
        log("fxml", "init start");
       /* try {
            List<FxSavePoint> fxSavePoints = fxSavePointService.savePoints();
            savePoints.addAll(fxSavePoints);
        } catch (NoSuchElementException | IOException e){
            System.out.println("no save points");
        }
        explorerVBox.getChildren().addAll(savePoints);*/
    }

    @FXML
    void ExportSavePointsInFile(ActionEvent event) {

    }

    @FXML
    void addNewSavePoint(ActionEvent event) {

    }

    @FXML
    void closeApp(ActionEvent event) {

    }

    @FXML
    void getInfoAboutApp(ActionEvent event) {

    }


    @FXML
    void loadSavePointsFromFile(ActionEvent event) {
        System.out.println("asd");
//    event.getSource()
    }

    void playSavePoint(ActionEvent event) {

    }
}

