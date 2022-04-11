package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.mail.MessagingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.ArrayList;


public class ManagementController implements Initializable{
    //public class ManagementController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBatch;
    @FXML
    private ChoiceBox<String> choiceEmail;
    @FXML
    private TableColumn<OfficerRecord,String> colCom;
    @FXML
    private TextField companyTF;
    @FXML
    private TableColumn<OfficerRecord,String> colCon;
    @FXML
    private TableColumn<OfficerRecord, Date> colMed;
    @FXML
    private TableColumn<OfficerRecord, Date> colPSRA;
    @FXML
    private TableColumn<OfficerRecord, Date> colPolice;
    @FXML
    private TextField conTF;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<OfficerRecord,String> colFn;
    @FXML
    private TextField fnTF;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableColumn<OfficerRecord,Integer> colId;
    @FXML
    private TextField idTF;
    @FXML
    private Button btnExport;
    @FXML
    private Button insertBtn;
    @FXML
    private Button assignBtn;
    @FXML
    private Button btnRefresh;
    @FXML
    private TableColumn<OfficerRecord,String> colLn;
    @FXML
    private TextField lnTF;
    @FXML
    private TableView<OfficerRecord> recordView;
    @FXML
    private Button updateBtn;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileInputStream fileInputStream;

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
    @FXML
    void onAssignButtonClick(ActionEvent event) throws MessagingException {
        System.out.println("Assign Click!!");
        if (event.getSource() == assignBtn) {

            assignBatch();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Email");
            alert.setHeaderText("Send Notification");
            alert.setContentText("Would you also like to notify "+ fnTF.getText() +" "+ lnTF.getText()+" that they are being " +"assigned to Batch: "+ choiceBatch.getValue());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //System.out.println("Send Email");
                String newMsg = Notification.generateBatchNotification(choiceBatch.getValue(),fnTF.getText(),lnTF.getText());
                Email.sendMail("swenProjectEmailer@gmail.com","Training-Date",newMsg);
            }else{
                System.out.println("Cancel");
            }
        }
    }
    @FXML
    void refreshBtnClick(ActionEvent event){
        if (event.getSource() == btnRefresh){
            System.out.println("Refreshed");
            reloadtable();
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

    @FXML
    void exportBtnClick(ActionEvent event) throws SQLException {
        Connection conn = getConnection();
        if (event.getSource() == btnExport){
            System.out.println( "test");
            try{
                String query = "Select * from guardsdb";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("Officer Details");
                XSSFRow header = sheet.createRow(0);
                header.createCell(0).setCellValue("ID");
                header.createCell(1).setCellValue("First Name");
                header.createCell(2).setCellValue("Last Name");
                header.createCell(3).setCellValue("Company");
                header.createCell(4).setCellValue("Contact");
                header.createCell(5).setCellValue("Medical Expiry Date");
                header.createCell(6).setCellValue("Certification Expiry Date");
                header.createCell(7).setCellValue("PSRA Expiry Date");
                header.createCell(8).setCellValue("Assigned Batch");

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3,200*25);
                sheet.setColumnWidth(4,300*25);
                sheet.autoSizeColumn(5);
                sheet.autoSizeColumn(6);
                sheet.autoSizeColumn(7);
                sheet.autoSizeColumn(8);

                int index = 1;
                while(rs.next()){
                    XSSFRow row = sheet.createRow(index);
                    row.createCell(0).setCellValue(rs.getString("id"));
                    row.createCell(1).setCellValue(rs.getString("fname"));
                    row.createCell(2).setCellValue(rs.getString("lname"));
                    row.createCell(3).setCellValue(rs.getString("company"));
                    row.createCell(4).setCellValue(rs.getString("contact"));
                    row.createCell(5).setCellValue(rs.getString("medical_exp"));
                    row.createCell(6).setCellValue(rs.getString("police_rec_exp"));
                    row.createCell(7).setCellValue(rs.getString("psra_exp"));
                    row.createCell(8).setCellValue(rs.getString("batch_id"));
                    index++;
                }

                FileOutputStream fileOutputStream = new FileOutputStream("NewDetails.xlsx");
                wb.write(fileOutputStream);
                fileOutputStream.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Status");
                alert.setHeaderText("Information Successfully Exported");
                alert.setContentText("Press ok to continue");
                alert.showAndWait();

                pst.close();
                rs.close();

            }catch(SQLException | FileNotFoundException ex){
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertRecord(){
        System.out.println("Hi");
        searchTable();
    }

    private void deleteRecord(){
        String query = "DELETE FROM `guardsdb` WHERE id =" +idTF.getText();
        executeQuery(query);
        reloadtable();
    }

    private void updateRecord(){
        reloadtable();
        int id;
        String firstname,lastname,company,contact,batchID;
        reloadtable();
        firstname = fnTF.getText();
        lastname = lnTF.getText();
        company = companyTF.getText();
        contact = conTF.getText();
        batchID = "bc1001";
        //Check if the textfields are empty
        if(firstname.isEmpty() || lastname.isEmpty() || company.isEmpty() || contact.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            // Check if values are of correct type
        }else if(!firstname.matches("[a-zA-Z]+") || !lastname.matches("[a-zA-Z]+") || !company.matches("[a-zA-Z]+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter valid values");
                    alert.showAndWait();
        }else
        {
        String query = "UPDATE guardsdb SET fname = '"+firstname+"', lname ='"+ lastname +"', company ='"+ company +"', contact ='"+ contact +"'" +
                ", batch_id ='"+ batchID +"' where id = '"+idTF.getText()+"' ";
        executeQuery(query);
        //Success
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Status");
        alert.setHeaderText("Information Successfully Updated");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();
        reloadtable();
        }
    }

    private void assignBatch(){
        int id;
        String firstname,lastname,company,contact,batchID;
        reloadtable();
        firstname = fnTF.getText();
        lastname = lnTF.getText();
        company = companyTF.getText();
        contact = conTF.getText();
        batchID = choiceBatch.getValue();
        //Check if the textfields are empty
        if(firstname.isEmpty() || lastname.isEmpty() || company.isEmpty() || contact.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
        }else{

        System.out.println(batchID);
        String query = "UPDATE guardsdb SET fname = '"+firstname+"', lname ='"+ lastname +"', company ='"+ company +"', contact ='"+ contact +"'" +
                ", batch_id ='"+ batchID +"' where id = '"+idTF.getText()+"' ";
        executeQuery(query);

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
    ArrayList<String> batchChoice = new ArrayList<String>();

    //private String [] reasons ={"Documents","Re-Training","Custom"};
    ObservableList<OfficerRecord> oblist = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initialized");
        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from guardsdb");
            while(result.next()){
                oblist.add(new OfficerRecord(result.getInt("id"),result.getString("fname"),
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
        colPolice.setCellValueFactory(new PropertyValueFactory<>("psra_exp"));
        colPSRA.setCellValueFactory(new PropertyValueFactory<>("pol_exp"));
        recordView.setItems(oblist);
        setCellValue();

        Connection conn2 = getConnection();
        try {
            ResultSet result = conn2.createStatement().executeQuery("select * from batch");
            while (result.next()) {
                batchChoice.add(result.getString("batch_id"));
                System.out.println(batchChoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        batchChoice.add("Default");
        choiceBatch.setValue("Default");
        choiceBatch.getItems().addAll(batchChoice);


    }

    private void clearCell(){
        idTF.clear();
        fnTF.clear();
        lnTF.clear();
        companyTF.clear();
        conTF.clear();
    }
    //This method reloads the table whenever the record data is updated
    public void reloadtable(){
        recordView.getItems().clear();
        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from guardsdb");
            while(result.next()){
                oblist.add(new OfficerRecord(result.getInt("id"),result.getString("fname"),
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
        colPolice.setCellValueFactory(new PropertyValueFactory<>("psra_exp"));
        colPSRA.setCellValueFactory(new PropertyValueFactory<>("pol_exp"));
        recordView.setItems(oblist);
    }

    private void setCellValue(){
        recordView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                OfficerRecord mt = recordView.getItems().get(recordView.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(mt.getId()));
                fnTF.setText(mt.getFirstname());
                lnTF.setText(mt.getLastname());
                companyTF.setText(mt.getCompany());
                conTF.setText(mt.getContact());
            }
        });
    }
    // Allows the table to be searchable, essentially just recreates the ta
    @FXML
    public void searchTable(){
        FilteredList<OfficerRecord> filteredData = new FilteredList<>(oblist, b -> true);
        tfSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(officerRecord -> {
                if (newValue== null || newValue.isEmpty()){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (officerRecord.getFirstname().toLowerCase().contains(searchKeyword)){
                    return true;
                }else if(officerRecord.getLastname().toLowerCase().contains(searchKeyword)){
                    return true;
                }else return officerRecord.getCompany().toLowerCase().contains(searchKeyword);
            });
        });

        SortedList<OfficerRecord> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(recordView.comparatorProperty());
        recordView.setItems(sortedList);

    }

}


