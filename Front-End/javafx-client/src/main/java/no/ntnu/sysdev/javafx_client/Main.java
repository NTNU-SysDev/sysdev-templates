package no.ntnu.sysdev.javafx_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new FXMLLoader(getClass().getResource("/layout.fxml")).load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/main.css");

        stage.setTitle("JavaFX REST Client");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo.png")));

        stage.setMaxWidth(605);
        stage.setMaxHeight(455);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}