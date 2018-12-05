package main.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.classroot.Subject;

import java.sql.*;
import java.util.ArrayList;
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

    public void deleteDB(String  courseid) {
        try {
            System.err.println("Delete date: " + courseid);
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "Delete from subject  where COURSEID == \'" + courseid + "\' ";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Subject> getPreSubject(String preid) {
        ObservableList<Subject> preDB = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "select * from  subject where PREREQUISITE == \'" + preid + '\"';
                Statement statement = connection.createStatement();
                ResultSet rsquery = statement.executeQuery(query);
                while (rsquery.next()) {
                    String courseId = rsquery.getString(1);
                    String namecourse = rsquery.getString(2);
                    int year = rsquery.getInt(5);
                    int semeter = rsquery.getInt(6);
                    preDB.add(new Subject(courseId, namecourse, year, semeter));
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return preDB;

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
        return subjectDB;

    }

    public  ArrayList<String> checkPreReqSubject(String prereq) {
        ArrayList<String> pre = new ArrayList<String>();
        if (prereq.contains("/")) {
            String[] hold = prereq.split("/");
            String[] hold1 = hold[0].split("-");
            for (int i = 0; i < hold1.length; i++) {
                pre.add(hold1[i]);
            }
            pre.add("/");
            String[] hold2 = hold[1].split(",");
            for (int j = 0; j < hold2.length; j++) {
                pre.add(hold2[j]);
            }
            pre.add("3");
        } else {
            if (prereq.contains("/")) {
                String[] hold = prereq.split("/");
                for (int i = 0; i < hold.length; i++) {
                    pre.add(hold[i]);
                }
                pre.add("1");
            } else if (prereq.contains("-")) {
                String[] hold = prereq.split("-");
                for (int i = 0; i < hold.length; i++) {
                    pre.add(hold[i]);
                }
                pre.add("2");
            } else {
                pre.add(prereq);
                pre.add("4");
            }
        }
        return pre;
    }
    public Integer getPass(String courseid) {
        Connection conection = null;
        Statement stmt = null;
        int ans = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:SUBJECT.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject  WHERE COURSEID = \'" + courseid + "\'");
            while (rs.next()) {
                int pass = rs.getInt("pass");
                ans = pass;
            }
            rs.close();
            stmt.close();
            conection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ans;
    }

    public String getPreReq(String courseid) {
        Connection c = null;
        Statement stmt = null;
        String ans = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:subjectdb.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject WHERE COURSEID = \'" + courseid + "\'");
            while (rs.next()) {
                String prereq = rs.getString("prereq");
                ans = prereq;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ans;
    }
    public Map getAllPreReq() {
        Connection c = null;
        Statement stmt = null;
        Map<String, String> preReqDB = new LinkedHashMap<String, String>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:subjectdb.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject");
            while (rs.next()) {
                String courseid = rs.getString("courseid");
                String pre = rs.getString("prereq");
                preReqDB.put(courseid, pre);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return preReqDB;
    }
    public void updateData(String id, String name, String pre, int pass, String color, int year, int sem) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:subjectdb.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "UPDATE SUBJECT set NAME= \'" + name + "\',PREREQ= \'" + pre + "\',PASS=\'" + pass + "\',COLOR= \'" + color + "\',YEAR=\'" + year + "\',SEM=\'" + sem + "\' where COURSEID=\'" + id + "\'";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public Integer cHeckPass(String courseid) {

        int ans = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                String query = "select COURSEID  from  subject == \'" + courseid + '\"';
                Statement statement = connection.createStatement();
                ResultSet rsquery = statement.executeQuery(query);
                while (rsquery.next()) {
                    int pass = rsquery.getInt("PASSSTATUS");
                    ans = pass;

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public void updatePass(String courseid, int pass) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            if (connection != null) {
                String sql = "UPDATE subject set PASSSTATUS=\'" + pass + "\' WHERE COURSEID=\'" + courseid + "\'";

                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                connection.commit();

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Subject> showDBUser(int y, int s) {

            Connection conection = null;
            Statement stmt = null;
            ObservableList<Subject> subjects = FXCollections.observableArrayList();
            if (y > 4) {
                y = 4;
            }
            try {
                Class.forName("org.sqlite.JDBC");
                conection = DriverManager.getConnection("jdbc:sqlite:SUBJECT.db");
                conection.setAutoCommit(false);
                stmt = conection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM subject WHERE YEAR<=\'" + y + "\' AND SEM=\'" + s + "\'");
                while (rs.next()) {
                    String id = rs.getString("courseid");
                    String name = rs.getString("name");
                    String pre = rs.getString("prereq");
                    int pass = rs.getInt("pass");
                    String color = rs.getString("color");
                    int year = rs.getInt("year");
                    int sem = rs.getInt("sem");
                    subjects.add(new Subject(id, name, pre, pass, year, sem, color));
                }
                rs.close();
                stmt.close();
                conection.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            return subjects;
        }


    }

