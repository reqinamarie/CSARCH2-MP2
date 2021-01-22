package Simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("input page.fxml"));
        primaryStage.setTitle("FA - MRU Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.sizeToScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
