package com.proj.comp2171project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.*;

public class ScheduleController {

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

    private String tfTName;
    private String tfTLocation;
    private String tfTBatch;
    private String tfTDate;
    public ScheduleController(){}


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
        String query = "INSERT INTO batch VALUES(" + tfTBatch + ",'" + tfTName + "','" + tfTLocation +"','"+ tfTrainingDate.getValue()+"')";
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
}
