package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnSignin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    RecordController controller = new RecordController();

    Connection connection = DatabaseController.getConnection();


    @FXML
    void onSigninButtonClick(ActionEvent event) throws IOException {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        if (username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter username and password");
            alert.showAndWait();
        } else if (username.equals("Audit") && password.equals("Audit")) {
            NavbarController.switchToAuditSpecial(event);
            System.out.println("Auditor");

        }else{
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + username + "' AND password = '" + password + "'");
                if (rs.next()) {
                    System.out.println("Login Successful");
                    NavbarController.switchToDashboard(event);
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Username or password is incorrect");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    }




