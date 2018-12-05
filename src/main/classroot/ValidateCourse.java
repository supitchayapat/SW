package main.classroot;

import javafx.collections.ObservableList;
import main.DB.DBSubject;

public class ValidateCourse implements CheckCourseValidate {
    DBSubject dbSubject = new DBSubject();
    ObservableList<Subject> subjects;
    private String status = "0";

    @Override
    public boolean checkValidate(String coursePre) {
        dbSubject.connect();
        subjects = dbSubject.loadDate();
        for (Subject subject : subjects) {
            if (coursePre.equals(subject.getCOURSEID())) {
                 status = subject.getPREREQUSITE();

            }
        }
        return status.equals("1");
    }

    }

