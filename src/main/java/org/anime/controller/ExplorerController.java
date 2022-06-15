package org.anime.controller;

import com.sun.javafx.fxml.FXMLLoaderHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import org.anime.fxcomponent.FxSavePoint;
import org.anime.repository.SavePointRepository;
import org.anime.service.FxSavePointService;
import org.anime.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.anime.utils.StringUtils.log;

@Component
@Profile({"prod", "test"})
public class ExplorerController {
    @Autowired
    private SavePointRepository repository;
   /* private FxSavePointService fxSavePointService;

    public FxSavePointService getFxSavePointService() {
        return fxSavePointService;
    }

    @Autowired
    public void setFxSavePointService(FxSavePointService fxSavePointService) {
        this.fxSavePointService = fxSavePointService;
    }*/

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
    @FXML
    void initialize() {
        log("fxml", "init start");
        log("fxml", "repo " + (repository == null ? "is null" : "is init"));
       /* try {
            List<FxSavePoint> fxSavePoints = fxSavePointService.savePoints();
            savePoints.addAll(fxSavePoints);
        } catch (NoSuchElementException | IOException e){
            System.out.println("no save points");
        }
        explorerVBox.getChildren().addAll(savePoints);*/
    }

    @PostConstruct
    public void init() throws IOException {
        log("spring", "post consruct");
        log("spring", "repo " + (repository == null ? "is null" : "is init"));
        final FxSavePointService fxSavePointService = new FxSavePointService(repository);
        final List<FxSavePoint> fxSavePoints = fxSavePointService.savePoints();
        log("spring", "loaded savepoints");

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

