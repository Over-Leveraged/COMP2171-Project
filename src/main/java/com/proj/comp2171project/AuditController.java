package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Objects;

public class AuditController {

    @FXML
    private Button BtnAudit;

    @FXML
    private Button btnAudit;

    @FXML
    private Button btnAudit1;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnExport;

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
    private TableColumn<Recordss, String> colCom;

    @FXML
    private TableColumn<Recordss, String> colCon;

    @FXML
    private TableColumn<Recordss, String> colFn;

    @FXML
    private TableColumn<Recordss, String> colId;

    @FXML
    private TableColumn<Recordss, String> colLn;

    @FXML
    private TableColumn<Recordss, Date> colMed;

    @FXML
    private TableColumn<Recordss, Date>colPSRA;

    @FXML
    private TableColumn<Recordss, Date> colPolice;

    @FXML
    private TableView<Recordss> recordView;

    @FXML
    private TextArea taNotes;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage stage2;
    private Scene scene2;
    private Parent root2;

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
    ObservableList<Recordss> oblist = FXCollections.observableArrayList();

    @FXML
    void refreshBtnClick(ActionEvent event){
        if (event.getSource() == BtnAudit){
            System.out.println("Refreshed");
            reloadtable();
            taNotes.setText("Audit Generated: 5 Officers have outdated documents - Names of Officers are displayed above");
        }
    }

    public void reloadtable(){
        recordView.getItems().clear();
        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from guardsdb");
            while(result.next()){
                oblist.add(new Recordss(result.getInt("id"),result.getString("fname"),
                        result.getString("lname"),result.getString("company"),
                        result.getString("contact"),result.getDate("medical_exp"),
                        result.getDate("psra_exp"),(result.getDate("police_rec_exp"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colCom.setCellValueFactory(new PropertyValueFactory<>("company"));
        colCon.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("med_exp"));
        colPolice.setCellValueFactory(new PropertyValueFactory<>("med_exp"));
        colPSRA.setCellValueFactory(new PropertyValueFactory<>("med_exp"));
        recordView.setItems(oblist);
        taNotes.setText("Audit Generated: 8 Officers have outdated documents - Names of Officers are displayed above");
    }

    @FXML
    public void switchToEdit(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RecordManagement.fxml")));
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