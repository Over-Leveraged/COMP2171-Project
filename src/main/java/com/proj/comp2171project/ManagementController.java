package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {

    @FXML
    private TableColumn<ModelTabel,String> colCom;

    @FXML
    private TextField companyTF;

    @FXML
    private TableColumn<ModelTabel,String> colCon;

    @FXML
    private TextField conTF;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<ModelTabel,String> colFn;

    @FXML
    private TextField fnTF;

    @FXML
    private TableColumn<ModelTabel,Integer> colId;

    @FXML
    private TextField idTF;

    @FXML
    private Button insertBtn;

    @FXML
    private TableColumn<ModelTabel,String> colLn;

    @FXML
    private TextField lnTF;

    @FXML
    private TableView<ModelTabel> recordView;

    @FXML
    private Button updateBtn;

    @FXML
    void onButtonClick(ActionEvent event) {
        System.out.println("insert Clicked ME!!");
        if (event.getSource() == insertBtn) {
            insertRecord();
        }
    }
    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        System.out.println("Delete Click!!");
        if (event.getSource() == deleteBtn) {
            deleteRecord();
        }
    }
    @FXML
    void onUpdateButtonClick(ActionEvent event){
        System.out.println("Update Click!!");
        if (event.getSource() == updateBtn) {
            updateRecord();
        }
    }

    //Database Connection
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
        //String query = "INSERT INTO records(id,fname,lname,company) VALUES (" + tfid.getText() + "," + tfFname.getText() + "," + tfLname.getText() + "," + tfCompany.getText() +")";
        String query = "INSERT INTO guardsdb VALUES (" + idTF.getText() + ",'" + fnTF.getText() + "','" + lnTF.getText() + "','" + companyTF.getText() +"','" + conTF.getText() +"')";

        executeQuery(query);
        reloadtable();
    }

    private void deleteRecord(){
        String query = "DELETE FROM `guardsdb` WHERE id =" +idTF.getText();
        executeQuery(query);
        reloadtable();
    }

    private void updateRecord(){
        int id;
        String firstname,lastname,company,contact;
        reloadtable();
        //id = idTF.getText();
        firstname = fnTF.getText();
        lastname = lnTF.getText();
        company = companyTF.getText();
        contact = conTF.getText();


        String query = "UPDATE guardsdb SET fname = '"+firstname+"', lname ='"+ lastname +"', company ='"+ company +"', contact ='"+ contact +"' where id = '"+idTF.getText()+"' ";
        //String query = "UPDATE `guardsdb` SET `fname`='[value-2]',`lname`='[value-3]',`company`='[value-4]',`contact`='[value-5]',`medical_exp`='[value-6]' WHERE `id`= + idTF.getText()";
        reloadtable();

        executeQuery(query);
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
    ObservableList<ModelTabel> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //public void showrecord() {

        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from guardsdb");
            while(result.next()){
                oblist.add(new ModelTabel(result.getInt("id"),result.getString("fname"),
                        result.getString("lname"),result.getString("company"),result.getString("contact")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colCom.setCellValueFactory(new PropertyValueFactory<>("company"));
        colCon.setCellValueFactory(new PropertyValueFactory<>("contact"));

        recordView.setItems(oblist);
        setCellValue();

    }
    public void reloadtable(){

        recordView.getItems().clear();

        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from guardsdb");
            while(result.next()){
                oblist.add(new ModelTabel(result.getInt("id"),result.getString("fname"),
                        result.getString("lname"),result.getString("company"),result.getString("contact")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colCom.setCellValueFactory(new PropertyValueFactory<>("company"));
        colCon.setCellValueFactory(new PropertyValueFactory<>("contact"));

        recordView.setItems(oblist);

    }

    private void setCellValue(){
        recordView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ModelTabel mt = recordView.getItems().get(recordView.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(mt.getId()));
                fnTF.setText(mt.getFirstname());
                lnTF.setText(mt.getLastname());
                companyTF.setText(mt.getCompany());
                conTF.setText(mt.getContact());
            }
        });
    }
    private void clearCell(){
        idTF.clear();
        fnTF.clear();
        lnTF.clear();
        companyTF.clear();
        conTF.clear();
    }
}


