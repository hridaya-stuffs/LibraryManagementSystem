package com.java.LMS;

import com.java.LMS.controller.LibrarianController;
import com.java.LMS.model.LibrarianModel;
import com.java.LMS.view.LibrarianView;

public class Main {
    public static void main(String[] args) {
        LibrarianModel librarianModel = new LibrarianModel();
        LibrarianView librarianView = new LibrarianView(new LibrarianController()); // manually added the controller
        LibrarianController librarianController = new LibrarianController(librarianModel, librarianView);

        // Set the controller in the view
        librarianView.setController(librarianController);

    }
}
