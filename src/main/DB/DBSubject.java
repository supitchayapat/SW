package DB;

import classroot.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
}
