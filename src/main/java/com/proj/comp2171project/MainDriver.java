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
        FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("auditUi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 990, 710);
        //Scene scene = new Scene(fxmlLoader.load(), 487, 418);
        stage.setTitle("GBD Dash");
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add("dashstyles.css");
        System.out.println("Test");
    }

    public static void main(String[] args) {
        launch();
    }
}