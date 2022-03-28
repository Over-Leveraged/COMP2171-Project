package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

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

    @FXML
    private TableView<Recordss> tbvOfficer;

    @FXML
    private TableColumn<Recordss,String> tcFn;

    @FXML
    private TableColumn<Recordss,Integer> tcId;

    @FXML
    private TableColumn<Recordss,String> tcLn;

    @FXML
    private TableColumn<?, ?> tcNotify;

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
       Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
       stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene2 = new Scene(root2, 990, 710);
       stage2.setScene(scene2);
       stage2.show();
       System.out.println("Test");
   }

    @FXML
    public void switchToNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RecordUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");
    }




}
