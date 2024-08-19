package com.java.LMS.controller;

import com.java.LMS.model.LibrarianModel;
import com.java.LMS.view.LibrarianView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibrarianController {
    private LibrarianModel model;
    private LibrarianView view;

    Connection connection;
    Statement statement;

    String user = "root";
    String password = "Password@123";
    String host = "localhost";
    int port = 3306;

    String databaseName = "librarydatabase";
    String databaseType = "mysql";
    String jdbcDatabaseUrl = "jdbc:" + databaseType + "://" + host + ":" + port + "/" + databaseName;

    public LibrarianController(LibrarianModel model, LibrarianView view) {
        this.model = model;
        this.view = view;
        getConnection();
    }
    public LibrarianController(){
        getConnection();
    }
    private void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void createBook(String id, String title, String author, String price) {
        try {
            String sql = "INSERT INTO books (id, title, author, price) VALUES('" + id + "','" + title + "','" + author + "','" + price + "')";
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateBook(String id, String title, String author, String price) {
        try {
            String sql = "UPDATE books SET title='" + title + "', author='" + author + "', price='" + price + "' WHERE id='" + id + "'";
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteBook(String id) {
        try {
            String sql = "DELETE FROM books WHERE id='" + id + "'";
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ResultSet viewBooks() {
        try {
            String sql = "SELECT * FROM books";
            return statement.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}
