package classroot;

import DB.DBStudent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentProfileController {
    @FXML
    Label gendarLabel , campusLabel, YearLevelLabel,StudentLabel,lasrnameLabel,firstnameLabel,middleLabel;
    DBStudent dbStudent ;
    public void initialize(){
        dbStudent = new DBStudent();
        dbStudent.connect();
        ObservableList<StudentData> studentData = dbStudent.loadDate();
        for (int i = 0; i < studentData.size() ; i++) {
            if (i ==0  ){
                if(studentData.get(i).equals("0")){
                    gendarLabel.setText("Men");
                }
                else if (studentData.get(i).equals("1")) {
                    gendarLabel.setText("Femal");
                }
            }
            campusLabel.setText(studentData.get(i).getCampus());
        }


    }

}
