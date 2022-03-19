package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DashController {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnAudit;

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
    private Text lbLocation;

    @FXML
    private Text lbbread;

    @FXML
    private Pane showLocation;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Stage stage2;
    private Scene scene2;
    private Parent root2;

   /* public void handleClick(ActionEvent event){
        if (event.getSource() == btnNew){
            switchToNew(event);
        }
    }*/
   @FXML
   public void switchToEdit(ActionEvent event) throws IOException {
       Parent root2 = FXMLLoader.load(getClass().getResource("RecordManagement.fxml"));
       //FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("RecordUi.fxml"));
       stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene2 = new Scene(root2, 990, 710);
       stage2.setScene(scene2);
       stage2.show();
       System.out.println("Test");

   }

    @FXML
    public void switchToNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RecordUi.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("RecordUi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //Scene scene = new Scene(fxmlLoader.load(), 950, 600); My method
        scene = new Scene(root, 990, 710);
        //stage.setScene();
        //scene.getStylesheets().add("theme.css");
        //stage.setTitle("GBD Dash");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        System.out.println("Test");

    }



}
