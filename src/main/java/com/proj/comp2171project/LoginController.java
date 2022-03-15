package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnSignin;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    RecordController controller = new RecordController();

    @FXML
    void onSigninButtonClick(ActionEvent event) throws IOException {
        System.out.println("You Clicked ME!!");
        if (event.getSource() == btnSignin) {
            System.out.println("Clicked Signin");
            controller.switchToHome(event);
        }
    }



}

