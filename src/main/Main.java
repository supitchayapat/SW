import DB.DBStudent;
import classroot.StudentData;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle(" Information Register");
        primaryStage.setScene(new Scene(root, 1000, 900));
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}



