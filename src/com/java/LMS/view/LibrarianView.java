package com.java.LMS.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import com.java.LMS.controller.LibrarianController;

public class LibrarianView {
    private LibrarianController controller;

    public LibrarianView(LibrarianController controller) {
        this.controller = controller;

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
                controller.createBook(id, title, author, price);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = idField.getText();
                String title = titleField.getText();
                String author = authorField.getText();
                String price = priceField.getText();
                controller.updateBook(id, title, author, price);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = idField.getText();
                controller.deleteBook(id);
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String[] columns = {"ID", "Title", "Author", "Price"};
                JTable table = new JTable();
                DefaultTableModel model = new DefaultTableModel();
                table.setModel(model);
                model.setColumnIdentifiers(columns);

                ResultSet resultSet = controller.viewBooks();
                try {
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String title = resultSet.getString("title");
                        String author = resultSet.getString("author");
                        String price = resultSet.getString("price");
                        model.addRow(new String[]{id, title, author, price});
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

    public void setController(LibrarianController controller) {
        this.controller = controller;
    }
}
