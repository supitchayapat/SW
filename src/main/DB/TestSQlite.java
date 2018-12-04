package DB;

import classroot.StudentData;
import classroot.Subject;
import javafx.collections.ObservableList;

public class TestSQlite {
    public static void main(String[] args) {
        DBSubject sql = new DBSubject();
        DBStudent sqls = new DBStudent();
        sql.connect();
        sqls.connect();

        ObservableList<Subject> subjects  = sql.loadDate();
        for (Subject sub : subjects){
            System.out.print(sub.getCOURSEID()+ " ");
            System.out.print(sub.getNAMECOURSE()+ " ");
            System.out.print(sub.getPREREQUSITE()+ " ");
            System.out.print(sub.getPASSSTATUS()+ " ");
            System.out.print(sub.getCREDIT()+ " ");
            System.out.print(sub.getYEAR()+ " ");
            System.out.print(sub.getSEM()+ " ");
            System.out.println(sub.getCOLOR());

        }
        ObservableList<StudentData> studentData = sqls.loadDate();
        for (StudentData studentData1 : studentData){
            System.out.print(studentData1.getId()+ " ");
            System.out.print(studentData1.getName()+ " ");
            System.out.print(studentData1.getSem()+ " ");
            System.out.print(studentData1.getYear()+ " ");
            System.out.print(studentData1.getGendar()+ " ");
            System.out.print(studentData1.getLastname()+ " ");
            System.out.print(studentData1.getMiddlename()+ " ");
            System.out.println(studentData1.getCampus());
        }
    }
}
