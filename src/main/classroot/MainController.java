package classroot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    Button register;
    @FXML
    Button profile;
    @FXML
    Button transcripbtn;
    private String studentId;

    public void setStudentId(String id){
        this.studentId = id;
    }

    public void initialize(){
        System.out.println(studentId+"MainController");
    }

    public void profileButton(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/StudentProfile.fxml"));
            stage.setScene(new Scene((Parent) loader.load(), 1000, 900));
            StudentProfileController stdProfile = loader.getController();
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setPreRegiserCourse(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("setcourse.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1000, 900));
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setbtnTranscript(ActionEvent actionEvent) {
    }

//    public void btnLogin(ActionEvent actionEvent) {
//        dbStudent = new DBStudent();
//        dbStudent.connect();
//        ObservableList<StudentData> studentDatas = dbStudent.loadDate();
//        String textinput = textFieldid.getText();
//        System.out.println(textinput);
//        boolean setShow = false;
//        for (StudentData std : studentDatas) {
//            if (textinput.equals(std.getId())) {
//                setShow = true;
////                Button  button = (Button) actionEvent.getSource();
////                Stage stage = (Stage)  button.getScene().getWindow();
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
////                try {
////                    stage.setScene(new Scene((Parent)  loader.load(),1000,900));
////                    stage.show();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }else if (!(textinput.equals(std.getId()))){
////
////                Alert alert = new Alert(Alert.AlertType.ERROR,"Student Id Incorrect", ButtonType.CLOSE);
////                alert.showAndWait();
////
////            }
//            }
//
//        }
//        if (setShow) {
//            Button button = (Button) actionEvent.getSource();
//            Stage stage = (Stage) button.getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
//            try {
//                stage.setScene(new Scene((Parent) loader.load(), 1000, 900));
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Student Id Incorrect", ButtonType.CLOSE);
//            alert.showAndWait();
//
//        }
//
//
////    public void unWrongAction(ActionEvent actionEvent) {
////    }
////
////    public void unWrongMouse(MouseEvent mouseEvent) {
////    }
////
////    public void closeForget(MouseEvent mouseEvent) {
////    }
//    }
}
