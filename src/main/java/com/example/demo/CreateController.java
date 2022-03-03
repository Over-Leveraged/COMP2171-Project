package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class CreateController {

    ImageView imageView = new ImageView();

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<Records, String> colCompany;

    @FXML
    private TableColumn<Records, String> colFname;

    @FXML
    private TableColumn<Records, Integer> colID;

    @FXML
    private TableColumn<Records, String> colLname;

    @FXML
    private TableView<Records> tableRecords;

    @FXML
    private TextField tfCompany;

    @FXML
    private TextField tfFname;

    @FXML
    private TextField tfLname;

    @FXML
    private TextField tfid;

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        System.out.println("You Clicked ME!!");
        if (event.getSource() == btnSave) {
            insertRecord();
        }
    }
    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            System.out.println("New File");
            event.acceptTransferModes(TransferMode.ANY);
        }

    }
    @FXML
    void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        //ImageView imageView = new ImageView();

        imageView.setImage(img);
    }


    //public void initialize(URL url, ResourceBundle rb) {
        //showRecords();

    //}

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/officerrecords", "root", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }
    private void insertRecord(){
        //String query = "INSERT INTO records(id,fname,lname,company) VALUES (" + tfid.getText() + "," + tfFname.getText() + "," + tfLname.getText() + "," + tfCompany.getText() +")";
        String query = "INSERT INTO records VALUES (" + tfid.getText() + ",'" + tfFname.getText() + "','" + tfLname.getText() + "','" + tfCompany.getText() +"')";
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
}

   /* public ObservableList<Records> getRecords(){
        ObservableList<Records> recordList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from records";
        Statement st;
        ResultSet result;
        try{
            st = conn.createStatement();
            result = st.executeQuery(query);
            Records records;
            while(result.next()){
                records = new Records(result.getInt("id"),result.getString("fname"),result.getString("lname"),result.getString("company"));
                recordList.add(records);
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return recordList;

    }

    public void showRecords(){

        ObservableList<Records> list =  getRecords();

        colID.setCellValueFactory(new PropertyValueFactory<Records, Integer>("id"));
        colFname.setCellValueFactory(new PropertyValueFactory<Records, String>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<Records, String>("lname"));
        colCompany.setCellValueFactory(new PropertyValueFactory<Records, String>("company"));

        tableRecords.setItems(list);
    }


    } **/
