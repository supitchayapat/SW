package main.classroot;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import main.DB.DBStudent;
import main.DB.DBSubject;

import java.util.*;

public class OverviewCourseController {


    DBSubject  subjectDB = new DBSubject();
    DBStudent  userDB = new DBStudent();
    ObservableList<Subject> subjects ;

    @FXML
    private TableView<Subject> showSubjectDetail;
    @FXML
    private Button pass, nopassbtn;

    public void initialize() {
        setTableView();
        pass.setDisable(true);
        nopassbtn.setDisable(true);
        subjects = subjectDB.loadDate();

        showSubjectDetail.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (showSubjectDetail.getSelectionModel().getSelectedItem() != null) {
                    pass.setDisable(false);
                    nopassbtn.setDisable(false);
                }
            }
        });

    }

    public void setTableView() {
        int year = Calendar.getInstance().get(Calendar.YEAR)-1956;
        subjects = subjectDB.showDBUser(year-Integer.parseInt(userDB.getId().substring(0,2)),userDB.getSem());
        Collections.sort(subjects, new Comparator<Subject>() {
            @Override
            public int compare(Subject o1, Subject o2) {
                if (o1.getYEAR() > o2.getYEAR()) return 1;
                if (o1.getYEAR() < o2.getYEAR()) return -1;
                else {
                    if (o1.getSEM() > o2.getSEM()) return 1;
                    if (o1.getSEM() < o2.getSEM()) return -1;
                    return 0;
                }
            }
        });
        showSubjectDetail.setItems(subjects);
    }

    @FXML
    public void paassBtn(ActionEvent event) {
        int p1 = 0;
        int p2 = 0;
        if (showSubjectDetail.getSelectionModel().getSelectedItem() != null) {
            ArrayList<String> h = subjectDB.checkPreReqSubject(showSubjectDetail.getSelectionModel().getSelectedItem().getPREREQUSITE());
            for (int i = 0; i < h.size() - 1; i++) {
                if (Integer.parseInt(h.get(h.size() - 1)) == 3) {
                    if (!h.get(i).equals("/")) {
                        if (subjectDB.getPass(h.get(i)) ==  1) {
                            p2 += 1;
                        }
                    } else {
                        p1 = p2;
                        p2 = 0;
                    }
                    if (p1 == h.indexOf("/") && p2 >= 1) {
                        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 1);
                    }
                } else if (Integer.parseInt(h.get(h.size() - 1)) == 1) {
                    if (subjectDB.getPass(h.get(i)) == 1) {
                        p1 = 1;
                    }
                    if (p1 == 1) {
                        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 1);
                    }
                } else if (Integer.parseInt(h.get(h.size() - 1)) == 2) {
                    if (subjectDB.getPass(h.get(i)) == 1) {
                        p1 += 1;
                    }
                    if (p1 == h.size() - 1) {
                        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 1);
                    }
                } else if (Integer.parseInt(h.get(h.size() - 1)) == 4) {
                    if (subjectDB.getPass(h.get(i)) == 1) {
                        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 1);
                    } else if (h.get(i).equals("na")) {
                        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 1);
                    } else if (subjectDB.getPass(h.get(i)) == 0) {
                        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 0);
                    }
                }
            }
        }
        if (subjectDB.getPass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID())==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Should pass prereq");
            alert.setContentText("You should pass "+subjectDB.getPreReq(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID())+ " before pass this subject!");
            alert.showAndWait();
        }
        setTableView();
    }

    public void changeNotPass() {
        subjectDB.updatePass(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID(), 0);
        if (showSubjectDetail.getSelectionModel().getSelectedItem() != null) {
            Map<String, String> h = subjectDB.getAllPreReq();
            List<String> val = new ArrayList<String>(h.values());
            List<String> key = new ArrayList<String>(h.keySet());
            List<String> courseidCollect = new ArrayList<String>();
            courseidCollect.add(showSubjectDetail.getSelectionModel().getSelectedItem().getCOURSEID());
            int c = 0;
            while (c < courseidCollect.size()) {
                for (int i = 0; i < val.size(); i++) {
                    if (val.get(i).contains("/")) {
                        String[] mid = val.get(i).split("/");
                        String[] hold1 = mid[0].split(",");
                        for (int j = 0; j < hold1.length; j++) {
                            if (hold1[j].contains(courseidCollect.get(c))) {
                                courseidCollect.add(key.get(i));
                                subjectDB.updatePass(key.get(i), 0);
                            }
                        }

                        int count = 0;
                        String[] hold2 = mid[1].split(",");
                        if (val.get(i).contains(courseidCollect.get(c))) {
                            for (int j = 0; j < hold2.length; j++) {
                                if (subjectDB.getPass(hold2[j]) == 1) {
                                    count++;
                                }
                                if (count == 1 && j == hold2.length - 1) {
                                    courseidCollect.add(key.get(i));
                                    subjectDB.updatePass(key.get(i), 0);
                                }
                            }
                        }


                    } else if (val.get(i).contains("-")) {
                        String[] hold1 = val.get(i).split("-");
                        for (int j = 0; j < hold1.length; j++) {
                            if (hold1[j].contains(courseidCollect.get(c))) {
                                courseidCollect.add(key.get(i));
                                subjectDB.updatePass(key.get(i), 0);
                            }
                        }
                    } else if (val.get(i).contains(",")) {
                        int count = 1;
                        String[] hold1 = val.get(i).split(",");
                        if (val.get(i).contains(courseidCollect.get(c))) {
                            for (int j = 0; j < hold1.length; j++) {
                                if (subjectDB.getPass(hold1[j]) == 1) {
                                    count++;
                                }
                                if (count == 1 && j == hold1.length - 1) {
                                    courseidCollect.add(key.get(i));
                                    subjectDB.updatePass(key.get(i), 0);
                                }
                            }
                        }
                    } else {
                        if (val.get(i).contains(courseidCollect.get(c))) {
                            subjectDB.updatePass(key.get(i), 0);
                            courseidCollect.add(key.get(i));
                        }
                    }
                }
                c++;
            }
            if (showSubjectDetail.getSelectionModel().getSelectedItem().getPASSSTATUS()==0 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Should NOTPASS only pass subject");
                alert.setContentText("You should click NOTPASS in pass subject!");
                alert.showAndWait();
            }
            setTableView();
        }
        pass.setDisable(true);
    }

    @FXML
    public void nopassBtn(ActionEvent event) {
        changeNotPass();
        nopassbtn.setDisable(true);
    }
}

