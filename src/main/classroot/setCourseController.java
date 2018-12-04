package classroot;

import DB.DBStudent;
import DB.DBSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;


public class setCourseController {
    DBSubject dbSubject = new DBSubject();
    DBStudent dbStudent = new DBStudent();
    ObservableList<Subject> users;
    Map<String, String> sub = dbSubject.getIdSubject();
    @FXML
    TextField courseidsearch;
    @FXML
    Button Ssearchcourse;
    @FXML
    TableView tableVlewDetail;
    @FXML
    TableView tableVlewPre;
    @FXML
    TableColumn course_id, course_id1, name, name1, semeter, semeter1, Year, Year1;
    @FXML
    Button showStatus;
    @FXML
    Label showBeacuse;

    @FXML
    void initialize() {
        dbSubject.connect();
        dbStudent.connect();
        users = dbSubject.loadDate();
//        ObservableList<Subject> users = dbSubject.loadDate();
        ArrayList<String> predictSubject = new ArrayList<>(sub.keySet());
        TextFields.bindAutoCompletion(courseidsearch, predictSubject);

//        if (users.isEmpty()) {
//            courseidsearch.setText(" ");
//            name.setText(" ");
//            semeter.setText(" ");
//            Year.setText(" ");

    }


    public void btnSearch(ActionEvent actionEvent) {

//        tableVlewDetail.setItems(users);
        ObservableList<Subject> subjectsshow = FXCollections.observableArrayList();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(courseidsearch.getText());
            if (users.get(i).getCOURSEID().equals(courseidsearch.getText())) {
                System.out.println(40);
                subjectsshow.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
                tableVlewDetail.setItems(subjectsshow);
                ObservableList<Subject> persubject  = FXCollections.observableArrayList();
                String intString = users.get(i).getPREREQUSITE();
                if (intString.contains("/")) {
                    String[] orSubject = intString.split("/");
                    for (int j = 0; i < orSubject.length; j++) {
                        for (int k = 0; k < users.size(); k++) {
                            if (orSubject[j].equals(users.get(k).getCOURSEID())) {
                                persubject.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
                                tableVlewDetail.setItems(subjectsshow);

                            }

                        }

                    }
                } else if (intString.equals("+")) {
                    String[] orSubject = intString.split("/");
                    for (int j = 0; i < orSubject.length; j++) {
                        for (int k = 0; k < users.size(); k++) {
                            if (orSubject[j].equals(users.get(k).getCOURSEID())) {
                                persubject.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
                                tableVlewDetail.setItems(subjectsshow);

                            }

                        }

                    }

                } else {
                    for (int k = 0; k < users.size(); k++) {
                        if (intString.equals(users.get(k).getCOURSEID())) {
                            persubject.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
                            tableVlewDetail.setItems(subjectsshow);
                        }

                    }
                }
            }


//        course_id.setText(dbSubject.loadDate().get(1).getCOURSEID());
//        if (course_id.getText().equals("")) {
//            course_id.setText("No data");
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Wrong CourseID");
//            alert.setHeaderText(null);
//            alert.setContentText("No CourseID");
//            alert.showAndWait();

//        }
        }
    }
}

//
//    }

