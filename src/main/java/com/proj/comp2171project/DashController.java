package com.proj.comp2171project;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DashController implements Initializable {

    // STAGE INFORMATION FOR SWITCHING SCENES AT THE NAV BAR
    private Stage stage;
    private Scene scene;
    private Parent root;

    // BUTTONS AND TEXT FIELD FUNCTIONALITIES
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnLogout;
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
    private Label labelTime;

    @FXML
    private Text lbLocation;

    @FXML
    private Text lbbread;

    @FXML
    private Pane showLocation;

    @FXML
    private Button EditSchedule;

    // MAIN TABLE COLUMNS AND TABLE VIEW OPTIONS

    @FXML
    private TableView<OfficerRecord> tbvOfficer;

    @FXML
    private TableColumn<OfficerRecord,String> tcFn;

    @FXML
    private TableColumn<OfficerRecord,Integer> tcId;

    @FXML
    private TableColumn<OfficerRecord,String> tcLn;

    @FXML
    private TableColumn<?, ?> tcNotify;

    // BATCH TABLE COLUMNS AND TABLE VIEW OPTIONS
    @FXML
    private TableView<Batch> BatchView;

    @FXML
    private TableColumn<Batch, String> colBatch;

    @FXML
    private TableColumn<Batch, Date> colBatchDate;

    @FXML
    private TableColumn<Batch, String> colLocation;
    private volatile boolean stop = false;


    //////////////////////ACTION EVENT BUTTONS FOR SWITCHING SCENES FROM THE DASHBOARD//////////////////////////////////

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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @FXML
    public void loadTables(ActionEvent event){
        System.out.println("Load Table");
        loadtable();
    }

    /////////////////////////////////////////DATABASE CONNECTION////////////////////////////////////////////////////////
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/guardsdb", "root", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    ////////////////////////////////// BATCH TABLE POPULATION///////////////////////////////////////////////////////////
    ObservableList<Batch> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //public void loadtable(){
        Timenow();
        System.out.println("Initialized");
        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from batch");
            while(result.next()){
                oblist.add(new Batch(
                        result.getString("batch_id"),
                        result.getString("t_name"),
                        result.getString("location"),
                        result.getDate("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colBatchDate.setCellValueFactory(new PropertyValueFactory<>("trainingDate"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        BatchView.setItems(oblist);
    }
    public void loadtable(){
        Connection conn = getConnection();
            try {
            ResultSet result = conn.createStatement().executeQuery("select * from batch");
            while(result.next()){
                oblist.add(new Batch(
                        result.getString("batch_id"),
                        result.getString("t_name"),
                        result.getString("location"),
                        result.getDate("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

            colBatchDate.setCellValueFactory(new PropertyValueFactory<>("trainingDate"));
            colBatch.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
            colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            BatchView.setItems(oblist);
}

private void Timenow(){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
    String formattedDate = dtf.format(now);
    labelTime.setText(dtf.format(now));
    }
}


