package DB;

import classroot.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBSubject {
    private Connection connection = null;
    private static String url = "jdbc:sqlite:SUBJECT.db";

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            if (!connection.isClosed()) {
                System.out.println("Connection to SQLite");
            } else {
                System.out.println("Cannot Connection to SQLite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //getData
    public ObservableList<Subject> loadDate() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from subject";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String courseId = resultSet.getString(1);
                String namecourse = resultSet.getString(2);
                String prerequsite = resultSet.getString(3);
                int passstatus = resultSet.getInt(4);
                int year = resultSet.getInt(5);
                int semeter = resultSet.getInt(6);
                String color = resultSet.getString(7);
                int credit = resultSet.getInt(8);
                subjects.add(new Subject(courseId, namecourse, prerequsite, passstatus, year, semeter, color, credit));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return subjects;
    }

    //insert
    public void saveDate(int id, String date) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "insert into newdate(id, date) values (?, ?)";
                //update database by insert new record
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Connection to database has problem, otherwise insertion.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editDB(int id, int date, double price, int qty, double totalPrice) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "update OrderList set id=\'" + id + "\' ,date=\'" + date + "\' ,price=\'" + price + "\' ,qty=\'" + qty + "\' , totalPrice=\'" + totalPrice + "\' where date == \'" + date + "\'";
                System.out.println(query);
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB(int date) {
        try {
            System.err.println("Delete date: " + date);
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "Delete from OrderList where date == \'" + date + "\' ";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Map getPreSubject() {
        Map<String, String> preDB = new LinkedHashMap<String, String>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "select PREREQUSITE from  subject ";
                Statement statement = connection.createStatement();
                ResultSet rsquery = statement.executeQuery(query);
                while (rsquery.next()) {
                    String course_id = rsquery.getString(1);
                    String pre_id = rsquery.getString(1);
                    preDB.put(course_id, pre_id);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  preDB;

    }
    public Map getIdSubject() {
        Map<String, String> subjectDB = new LinkedHashMap<String, String>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "select COURSEID from  subject ";
                Statement statement = connection.createStatement();
                ResultSet rsquery = statement.executeQuery(query);
                while (rsquery.next()) {
                    String course_id = rsquery.getString(1);
                    String pre_id = rsquery.getString(1);
                    subjectDB.put(course_id, pre_id);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  subjectDB;

    }
//    public String getData(String courseid) {
//        Connection c = null;
//        Statement stmt = null;
//        String ans = "";
//        try {
//
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:subjectdb.db");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM SUBJECT WHERE COURSEID LIKE '%" + courseid + "%'");
//            while (rs.next()) {
//                String id = rs.getString("courseid");
//                String name = rs.getString("name");
//                String pre = rs.getString("prereq");
//                int pass = rs.getInt("pass");
//                String color = rs.getString("color");
//                int year = rs.getInt("year");
//                int sem = rs.getInt("sem");
//                ans = "Course ID: " + id + '\n' +
//                        "Subject Name: " + name + '\n' +
//                        "Pre Req: " + pre + '\n' +
//                        "Year: " + year + '\n' +
//                        "Semester: " + sem + '\n' +
//                        "Pass: " + pass + '\n' +
//                        "Color: " + color + '\n';
//            }
//            rs.close();
//            stmt.close();
//            c.close();
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        return ans;
    }





