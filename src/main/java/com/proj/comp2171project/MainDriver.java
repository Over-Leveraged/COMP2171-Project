package com.proj.comp2171project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainDriver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 990, 600);
        stage.setTitle("GBD Dash");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add("dashstyles.css");
        System.out.println("Test");
    }

    public static void main(String[] args) {
        launch();
    }
}