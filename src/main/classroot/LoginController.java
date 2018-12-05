package main.classroot;

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
import main.DB.DBStudent;

import java.io.IOException;

public class LoginController {
     DBStudent dbStudent;
     static  ObservableList<StudentData> stulist;
     String textinput;

    @FXML
    TextField textFieldid;
    @FXML
    Button login;

    public void initialize(){
        dbStudent = new DBStudent();
        dbStudent.connect();
        stulist = dbStudent.loadDate();
    }
    public void btnLogin(ActionEvent actionEvent) {
        textinput = textFieldid.getText();
        boolean  setShow = false;
        for (StudentData std : stulist) {
            if (textinput.equals(std.getId())) {
                setShow = true;
            }
        }
        if (setShow) {
            Button button = (Button) actionEvent.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MainView.fxml"));
                stage.setScene(new Scene((Parent) loader.load(),1000, 900));
                MainController mainController = loader.<MainController>getController();
                mainController.setStudentId(textinput);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Student Id Incorrect", ButtonType.CLOSE);
            alert.showAndWait();

        }
    }

}
