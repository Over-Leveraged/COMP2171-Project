package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
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
    private Button EditSchedule;

    // MAIN TABLE COLUMNS AND TABLE VIEW OPTIONS

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

    // BATCH TABLE COLUMNS AND TABLE VIEW OPTIONS
    @FXML
    private TableView<Batch> BatchView;

    @FXML
    private TableColumn<Batch, String> colBatch;

    @FXML
    private TableColumn<Batch, Date> colBatchDate;

    @FXML
    private TableColumn<Batch, String> colLocation;


    //////////////////////ACTION EVENT BUTTONS FOR SWITCHING SCENES FROM THE DASHBOARD//////////////////////////////////
   @FXML
   public void switchToEdit(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RecordManagement.fxml")));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root, 990, 710);
       stage.setScene(scene);
       stage.show();
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

    @FXML
    public void switchToSchedule(ActionEvent event) throws IOException {
        System.out.println("Schedule Test");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scheduleUi.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();

    }
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


}
