package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    private Button generateMessage;
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

    ////////////////////////////////////ACTIONS FOR SWITCHING SCREENS/////////////////////////////////////////////
    @FXML
    private void btnEditOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToEdit(event);
    }
    @FXML
    private void setBtnLogoutOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToLogin(event);
    }
    @FXML
    private void setBtnAuditOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToAudit(event);
    }
    @FXML
    private void setBtnHomeOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToDashboard(event);
    }
    @FXML
    private void setBtnNewOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToNew(event);
    }
    @FXML
    private void setBtnNotifyOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToEmail(event);
    }
    @FXML
    private void setBtnScheduleOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToSchedule(event);
    }
    @FXML
    private void setBtnUsersOnclick(ActionEvent event) throws IOException {
        NavbarController.switchToUsers(event);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private String [] reasons ={"Documents","Re-Training","Custom"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bxObjective.getItems().addAll(reasons);
        bxObjective.setValue("Custom");
    }

    @FXML
    void onButtonClick(ActionEvent event) throws MessagingException {
        System.out.println("insert Clicked ME!!");
        if (event.getSource() == btnEmail) {

            String receipient = tfReceipient.getText();
            String subject = tfSubject.getText();
            String message = taMsg.getText();
            if (receipient.equals("") || subject.equals("") || message.equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Please enter all fields");
                alert.showAndWait();
            } else {
            Email.sendMail("swenProjectEmailer@gmail.com",subject,message);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Email Sent to " + receipient);
            alert.showAndWait();
        }
    }
    }


    @FXML
   void getChoice(ActionEvent event){
        String choice = bxObjective.getValue();
        System.out.println(choice);
        if (Objects.equals(choice, "Documents")){
            tfSubject.setText("Documents Outstanding");
            taMsg.setText("<p>Dear [Name Plate],<br> You have Documents Outstanding or Expired, please bring them to our location at [Address] as soon as possible <br><br>Regards, <br>User User</p>");
        }else if (Objects.equals(choice, "Re-Training")){
            tfSubject.setText("Re-Training Scheduled");
            taMsg.setText("<p>Dear [Name Plate], <br> Your retraining is being scheduled for [Date] <br><br>Regards, <br>User User<p>");
        }else if (Objects.equals(choice, "Custom")){
            tfSubject.setText("");
            taMsg.setText("");
        }
    }

}
