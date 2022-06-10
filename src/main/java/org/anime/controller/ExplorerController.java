package org.anime.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import org.anime.fxcomponent.FxSavePoint;
import org.anime.service.FxSavePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller("explorerController")
@Profile({"prod", "test"})
public class ExplorerController {

    @Autowired
    private FxSavePointService fxSavePointService;

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
//
    /*@FXML
    public void initialize() {

    }

    @PostConstruct
    public void init() throws IOException {
        try {
            List<FxSavePoint> fxSavePoints = fxSavePointService.savePoints();
            savePoints.addAll(fxSavePoints);
        } catch (NoSuchElementException e){
            System.out.println("no save points");
        }
        explorerVBox.getChildren().addAll(savePoints);
    }*/
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
