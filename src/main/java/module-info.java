module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    //requires com.h2database;
    requires java.sql;
    requires ormlite.jdbc;
    requires ormlite.core;
    exports at.ac.fhcampuswien.fhmdb.models;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.controllers;
    opens at.ac.fhcampuswien.fhmdb.controllers to javafx.fxml;
}