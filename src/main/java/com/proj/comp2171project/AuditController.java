package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.proj.comp2171project.DatabaseController.getConnection;

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
    private TableColumn<OfficerRecord, String> colCom;
    @FXML
    private TableColumn<OfficerRecord, String> colCon;
    @FXML
    private TableColumn<OfficerRecord, String> colFn;
    @FXML
    private TableColumn<OfficerRecord, String> colId;
    @FXML
    private TableColumn<OfficerRecord, String> colLn;
    @FXML
    private TableColumn<OfficerRecord, Date> colMed;
    @FXML
    private TableColumn<OfficerRecord, Date>colPSRA;
    @FXML
    private TableColumn<OfficerRecord, Date> colPolice;
    @FXML
    private TableView<OfficerRecord> recordView;
    @FXML
    private TextArea taNotes;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    ObservableList<OfficerRecord> oblist = FXCollections.observableArrayList();

    @FXML
    void refreshBtnClick(ActionEvent event){
        if (event.getSource() == BtnAudit){
            System.out.println("Refreshed");
            reloadtable();
        }
    }

    public void reloadtable(){
        int count = 0;
        recordView.getItems().clear();
        Connection conn = getConnection();
        try {
            assert conn != null;
            ResultSet result = conn.createStatement().executeQuery("select * from guardsdb");
            while(result.next()){
                if (result.getDate("psra_exp").before(new Date()) || result.getDate("medical_exp").before(new Date()) || result.getDate("police_rec_exp").before(new Date())){
                    count++;
                    System.out.println(count);
                    taNotes.setText("Audit Generated: " + count + " Officers that have outdated documents - Names of Officers are displayed above");
                    System.out.println(result.getString("fname"));
                    oblist.add(new OfficerRecord(result.getInt("id"),result.getString("fname"),
                            result.getString("lname"),result.getString("company"),
                            result.getString("contact"),result.getDate("medical_exp"),
                            result.getDate("psra_exp"),(result.getDate("police_rec_exp"))));
             }
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
    }

    @FXML
    //compare dates to see which is greater
    public static boolean compareDates(Date date1, Date date2) {
        if (date1.after(date2)) {
            return true;
        }
        return false;
    }
    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


}