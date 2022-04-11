package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavbarController {

    private static Stage stage;
    private static Scene scene;
    private Parent root;

    @FXML
    public static void switchToEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("RecordManagement.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");
    }

    @FXML
    public static void switchToNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("RecordUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");
    }

    @FXML
    public static void switchToSchedule(ActionEvent event) throws IOException {
        System.out.println("Schedule Test");
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("scheduleUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public static void switchToEmail(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("EmailUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public static void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("Dashboard.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public static void switchToUsers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("UserUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public static void switchToAudit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("AuditUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public static void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("Login.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,  820, 487);
        stage.setScene(scene);
        stage.show();
    }
    public static void switchToAuditSpecial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavbarController.class.getResource("auditUiSpecial.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
    }

    //Delete record from database


}
