import DB.DBStudent;
import classroot.StudentData;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @FXML
    TextField textFieldid ;
    @FXML
    Button login ;
    DBStudent dbStudent ;
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle(" Information Register");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void profileButton(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfile.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1000, 1000));
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setPreRegiserCourse(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerview.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1000, 1000));
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setbtnTranscript(ActionEvent actionEvent) {
    }

    public void btnLogin(ActionEvent actionEvent) {
        dbStudent = new DBStudent();
        dbStudent.connect();
        ObservableList<StudentData> studentDatas = dbStudent.loadDate();
        String textinput = textFieldid.getText();
        for (StudentData std : studentDatas) {
            if (textinput.equals(std.getId())){
                Button  button = (Button) actionEvent.getSource();
                Stage stage = (Stage)  button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
                try {
                    stage.setScene(new Scene((Parent)  loader.load(),1000,1000));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void unWrongAction(ActionEvent actionEvent) {
//    }
//
//    public void unWrongMouse(MouseEvent mouseEvent) {
//    }
//
//    public void closeForget(MouseEvent mouseEvent) {
//    }
}
