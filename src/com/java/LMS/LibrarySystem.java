package com.java.LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibrarySystem {
    Connection connection;
    Statement statement;

    String user = "root";
    String password = "root";
    String host = "localhost";
    int port = 3306;

    String databaseName = "librarydatabase";
    String databaseType = "mysql";
    String jdbcDatabaseUrl = "jdbc:" + databaseType + "://" + host + ":" + port + "/" + databaseName;

    LibrarySystem() {
        getConnection();
        JFrame frame = new JFrame("Library System");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600, 600);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(20, 20, 50, 10);
        panel.add(idLabel);
        JTextField idField = new JTextField();
        idField.setBounds(80, 20, 50, 20);
        panel.add(idField);

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setBounds(20, 50, 100, 10);
        panel.add(titleLabel);
        JTextField titleField = new JTextField();
        titleField.setBounds(80, 50, 200, 20);
        panel.add(titleField);

        JLabel authorLabel = new JLabel("Author");
        authorLabel.setBounds(20, 80, 100, 10);
        panel.add(authorLabel);
        JTextField authorField = new JTextField();
        authorField.setBounds(80, 80, 200, 20);
        panel.add(authorField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(20, 110, 100, 10);
        panel.add(priceLabel);
        JTextField priceField = new JTextField();
        priceField.setBounds(80, 110, 100, 20);
        panel.add(priceField);

        JButton createButton = new JButton("Create");
        createButton.setBounds(20, 140, 100, 30);
        panel.add(createButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(140, 140, 100, 30);
        panel.add(deleteButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(260, 140, 100, 30);
        panel.add(updateButton);

        JButton viewButton = new JButton("View");
        viewButton.setBounds(380, 140, 100, 30);
        panel.add(viewButton);

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = idField.getText();
                String title = titleField.getText();
                String author = authorField.getText();
                String price = priceField.getText();
                try {
                    String sql = "INSERT INTO books (id, title, author, price) " +
                            "VALUES('" + id + "','" + title + "','" + author + "','" + price + "')";
                    statement.execute(sql);
                    JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = idField.getText();
                String title = titleField.getText();
                String author = authorField.getText();
                String price = priceField.getText();
                try {
                    String sql =
                            "UPDATE books SET title='" + title + "',author='"
                                    + author + "',price='" + price + "' WHERE id='" + id + "'";
                    statement.execute(sql);
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = idField.getText();
                try {
                    String sql =
                            "DELETE FROM books WHERE id='" + id + "'";
                    statement.execute(sql);
                    JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String[] columns = {"ID", "Title", "Author", "Price"};
                JTable table = new JTable();
                DefaultTableModel model = new DefaultTableModel();
                table.setModel(model);
                model.setColumnIdentifiers(columns);

                try {
                    String sql =
                            "SELECT * FROM books";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String id = idField.getText();
                        String title = titleField.getText();
                        String author = authorField.getText();
                        String price = priceField.getText();
                        model.addRow(
                                new String[]{id, title, author, price}
                        );
                    }
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(20, 200, 500, 200);
                    panel.add(scrollPane);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new LibrarySystem();
                    }
                }
        );
    }

    void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
