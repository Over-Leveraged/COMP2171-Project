package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailController implements Initializable {

    @FXML
    private Button btnAudit;

    @FXML
    private Button btnAudit1;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnNotify;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnEmail;

    @FXML
    private ChoiceBox<String> bxObjective;
    @FXML
    private TextArea taMsg;

    @FXML
    private TextField tfReceipient;

    @FXML
    private TextField tfSubject;

    @FXML
    void switchToEdit(ActionEvent event) {

    }

    @FXML
    void switchToNew(ActionEvent event) {

    }
    private String [] reasons ={"Documents","Re-Training"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bxObjective.getItems().addAll(reasons);

    }
    @FXML
    void onButtonClick(ActionEvent event) throws MessagingException {
        System.out.println("insert Clicked ME!!");
        if (event.getSource() == btnEmail) {
            //System.out.println("Email Clicked");
            String receipient = tfReceipient.getText();
            String subject = tfSubject.getText();
            String message = taMsg.getText();
            //System.out.println(subj);
            //System.out.println(receipient);
            //System.out.println(message);
            Email.sendMail("swenProjectEmailer@gmail.com",subject,message);
        }
    }

}
