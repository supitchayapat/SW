package main.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.classroot.StudentData;

import java.sql.*;

public class DBStudent {
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
    public ObservableList<StudentData> loadDate() {
        ObservableList<StudentData> studentData = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from Student";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name  = resultSet.getString(2);
                int  sem = resultSet.getInt(3);
                int year  = resultSet.getInt(4);
                String  gender  = resultSet.getString(5);
                String lastname  = resultSet.getString(6);
                String middlename = resultSet.getString(7);
                String campus = resultSet.getString(8);
                studentData.add(new StudentData(id,name,sem,year,gender,lastname,middlename ,campus));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return studentData;
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
    public String getId() {
        Connection c = null;
        Statement stmt = null;
        String ans = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:userdb.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                String id = rs.getString("id");
                ans = id;
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
    public Integer getSem() {
        Connection c = null;
        Statement stmt = null;
        int ans = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:userdb.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                int sem = rs.getInt("sem");
                ans = sem;
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

}
