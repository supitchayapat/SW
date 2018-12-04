package classroot;

import DB.DBStudent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentProfileController {
    @FXML
    Label gendarLabel;
    @FXML
    Label campusLabel;
    @FXML
    Label YearLevelLabel;
    @FXML
    Label StudentLabel;
    @FXML
    Label lasrnameLabel;
    @FXML
    Label firstnameLabel;
    @FXML
    Label middleLabel;
    @FXML
    Button showcourse;

    ObservableList<StudentData> studentData = LoginController.stulist;
    String studentId;
    DBStudent dbStudent=  new DBStudent();


    public void initialize() {
        dbStudent.connect();
        ObservableList<StudentData> studentData = dbStudent.loadDate();
//        System.out.println(studentId);
        for (StudentData student : studentData) {
            System.out.println(1);
            if (student.getId().equals(studentData.get(1).getId())) {
                System.out.println(student.getId());

                StudentLabel.setText(student.getId());
                firstnameLabel.setText(student.getName());
                campusLabel.setText(student.getCampus());
                YearLevelLabel.setText(String.valueOf(student.getYear()));
                lasrnameLabel.setText(student.getLastname());
                middleLabel.setText(student.getMiddlename());
                if (student.getGendar().equals("0")) {
                    gendarLabel.setText("Man");
                } else if (student.getGendar().equals("1")) {
                    gendarLabel.setText("Female");
                }


            }
        }
    }


    public void btnShowcourse(ActionEvent actionEvent) {
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
}
