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
    ObservableList<Subject> persubject = FXCollections.observableArrayList();

    private String prere;
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
                prere = users.get(i).getPREREQUSITE();
                System.out.println(prere);
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
        String[] arraySubject ;
        if (prere.contains("-")){
            System.out.println("ss");
            arraySubject = prere.split("-");
            for (int i = 0 ; i < arraySubject.length ; i++){
                for (int j = 0; j < users.size();j++){
                    if (arraySubject[i].equals(users.get(j).getCOURSEID())) {
                        persubject.add(new Subject(users.get(j).getCOURSEID(),users.get(j).getNAMECOURSE(),users.get(j).getSEM(),users.get(j).getYEAR()));
                    }

                    }
            }

        }else if (persubject.contains("/")){
            System.out.println("ss");

            arraySubject = prere.split("-");
            for (int i = 0 ; i < arraySubject.length ; i++){
                for (int j = 0; j < users.size();j++){
                    if (arraySubject[i].equals(users.get(j).getCOURSEID())) {
                        persubject.add(new Subject(users.get(j).getCOURSEID(),users.get(j).getNAMECOURSE(),users.get(j).getSEM(),users.get(j).getYEAR()));
                    }

                }
            }

        }else if (!(persubject.contains("/") &&(persubject.contains("-")))){
            System.out.println("ss");

            for (int j = 0; j < users.size();j++){
                if (persubject.equals(users.get(j).getCOURSEID())) {
                    persubject.add(new Subject(users.get(j).getCOURSEID(),users.get(j).getNAMECOURSE(),users.get(j).getSEM(),users.get(j).getYEAR()));
                }

            }
        }
        System.out.println(persubject.toString());
        tableVlewPre.setItems(persubject);
        }


    }


//
//    }

