import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try (InputStream resourceAsStream = getClass().getResourceAsStream("hello-view.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
//      Pane panel = (Pane) fxmlLoader.load(resourceAsStream);
//      fxmlLoader.setLocation(url);
//    InputStream inputStream = HelloApplication.class.getResource("/hello-view.fxml").openStream();
//      Parent root = fxmlLoader.load(resourceAsStream);
            final ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/jutsu.png"))));
            Parent root = new VBox(imageView);

//      new VBox()
            Scene scene = new Scene(root);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
