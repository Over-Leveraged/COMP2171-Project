package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScheduleController implements Initializable {

    @FXML
    private DatePicker tfTrainingDate ;
    @FXML
    private TextField tfTrainingName;
    @FXML
    private TextField tfTrainingLocation;
    @FXML
    private TextField tfBatchNumber;
    @FXML
    private Button btnSave;
    // BATCH TABLE COLUMNS AND TABLE VIEW OPTIONS
    @FXML
    private TableView<Batch> BatchViews;
    @FXML
    private TableColumn<Batch, String> colBatch;
    @FXML
    private TableColumn<Batch, Date> colBatchDate;
    @FXML
    private TableColumn<Batch, String> colLocation;
    @FXML
    private TableColumn<Batch, String> colBatchName;

    private String tfTName;
    private String tfTLocation;
    private String tfTBatch;
    private String tfTDate;



    @FXML
    void saveBtnClick(ActionEvent event){
        System.out.println("Save Click!!");
        if (event.getSource() == this.btnSave) {
            this.insertRecord();
        }
    }
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
    private void insertRecord(){
        tfTBatch = tfBatchNumber.getText();
        tfTName=tfTrainingName.getText();
        tfTLocation=tfTrainingLocation.getText();
        String query = "INSERT INTO batch VALUES('" + tfTBatch + "','" + tfTName + "','" + tfTLocation +"','"+ tfTrainingDate.getValue()+"')";
        reloadtable();
        executeQuery(query);
        clearCell();


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

    private void clearCell(){
        tfBatchNumber.clear();
        tfTrainingName.clear();
        tfTrainingLocation.clear();
    }
    ////////////////////////////////// BATCH TABLE POPULATION///////////////////////////////////////////////////////////
    ObservableList<Batch> oblist = FXCollections.observableArrayList();


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
                System.out.println(result.getString("t_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colBatchDate.setCellValueFactory(new PropertyValueFactory<>("trainingDate"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colBatchName.setCellValueFactory(new PropertyValueFactory<>("batchName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        BatchViews.setItems(oblist);
    }

    public void reloadtable(){
        System.out.println("Reloaded");
        BatchViews.getItems().clear();
        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from batch");
            while(result.next()){
                oblist.add(new Batch(
                        result.getString("batch_id"),
                        result.getString("t_name"),
                        result.getString("location"),
                        result.getDate("date")));
                System.out.println(result.getString("t_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colBatchDate.setCellValueFactory(new PropertyValueFactory<>("trainingDate"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colBatchName.setCellValueFactory(new PropertyValueFactory<>("batchName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        BatchViews.setItems(oblist);
    }
}
