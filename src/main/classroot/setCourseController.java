package classroot;

import DB.DBStudent;
import DB.DBSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.Map;


public class setCourseController {
    DBSubject dbSubject = new DBSubject();
    DBStudent dbStudent = new DBStudent();
    ObservableList<Subject> users;
    Map<String, String> sub = dbSubject.getIdSubject();
    private boolean validateSubject = false;

    private String prere;
    @FXML
    TextField courseidsearch;
    @FXML
    Button Ssearchcourse;
    @FXML
    TableView tableVlewDetail,tableVlewPre;
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
        ObservableList<Subject> subjectsshow = FXCollections.observableArrayList();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getCOURSEID().equals(courseidsearch.getText())) {
                System.out.println(40);
                subjectsshow.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
                tableVlewDetail.setItems(subjectsshow);
                prere = users.get(i).getPREREQUSITE();
                break;
//                if (intString.contains("/")) {
//                    String[] orSubject = intString.split("/");
//                    for (int j = 0; i < orSubject.length; j++) {
//                        System.out.println(intString+"cld");
//
//                        for (int k = 0; k < users.size(); k++) {
//                            if (orSubject[j].equals(users.get(k).getCOURSEID())) {
//                                persubject.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
//                                tableVlewPre.setItems(persubject);
//                                break;
//
//                            }
//
//                        }
//
//                    }
//                } else if (intString.equals("+")) {
//                    System.out.println(intString+"cld");
//                    String[] orSubject = intString.split("/");
//                    for (int j = 0; i < orSubject.length; j++) {
//                        for (int k = 0; k < users.size(); k++) {
//                            if (orSubject[j].equals(users.get(k).getCOURSEID())) {
//                                persubject.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
//                                tableVlewPre.setItems(persubject);
//                                break;
//
//                            }
//
//                        }
//
//
//                    }
//
//                } else {
//                    for (int k = 0; k < users.size(); k++) {
//                        System.out.println(intString+"cld");
//
//                        if (intString.equals(users.get(k).getCOURSEID())) {
//                            persubject.add(new Subject(users.get(i).getCOURSEID(), users.get(i).getNAMECOURSE(), users.get(i).getSEM(), users.get(i).getYEAR()));
//                            tableVlewPre.setItems(persubject);
//                            break;
//                        }
//
//                    }
//                }
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
        String statusPass;

        ObservableList<Subject> persubject = FXCollections.observableArrayList();
        String[] arraySubject ;
        if (prere.contains("-")){
            arraySubject = prere.split("-");
            for (int i = 0 ; i < arraySubject.length ; i++){
                for (int j = 0; j < users.size(); j++){
                    if (arraySubject[i].equals(users.get(j).getCOURSEID())) {
                        statusPass = users.get(i).getPREREQUSITE();
                        if (statusPass.equals("1")){
                            validateSubject = true;
                        }else if (statusPass.equals("0")){
                            validateSubject = false;
                        }
                        persubject.add(new Subject(users.get(j).getCOURSEID(),users.get(j).getNAMECOURSE(),users.get(j).getSEM(),users.get(j).getYEAR()));
                    }
                }
            }

        }else if (prere.contains("/")){
            arraySubject = prere.split("/");
            for (int i = 0 ; i < arraySubject.length ; i++){
                for (int j = 0; j < users.size();j++){
                    if (arraySubject[i].equals(users.get(j).getCOURSEID())) {
                        statusPass = users.get(i).getPREREQUSITE();
                        if (statusPass.equals("1")){
                            validateSubject = true;
                        }
                        persubject.add(new Subject(users.get(j).getCOURSEID(),users.get(j).getNAMECOURSE(),users.get(j).getSEM(),users.get(j).getYEAR()));
                    }
                }
            }

        }else if (!(prere.contains("/") &&(prere.contains("-")))){

            for (int j = 0; j < users.size();j++){
                if (prere.equals(users.get(j).getCOURSEID())) {
                    statusPass = users.get(j).getPREREQUSITE();
                    if (statusPass.equals("1")){
                        validateSubject = true;
                    }
                    persubject.add(new Subject(users.get(j).getCOURSEID(),users.get(j).getNAMECOURSE(),users.get(j).getSEM(),users.get(j).getYEAR()));
                }
            }
        }
        tableVlewPre.setItems(persubject);
        if (validateSubject){
            System.out.println("print");
            showStatus.setStyle("-fx-border-color: #00ff00");
            showBeacuse.setText("Course registration is allowed.");
            showBeacuse.setStyle("-fx-border-color: #ff0000");
        }

    }
        }

//
//    }

