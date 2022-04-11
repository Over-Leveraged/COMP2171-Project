package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private ChoiceBox<String> cbBatch;

    // TRAINING TABLE COLUMNS AND TABLE VIEW OPTIONS
    @FXML
    private TableView<OfficerRecord> officerTv;
    @FXML
    private TableColumn<OfficerRecord, String> colBatchID;
    @FXML
    private TableColumn<OfficerRecord, Integer> colId; // ID
    @FXML
    private TableColumn<OfficerRecord, String> colfn;
    @FXML
    private TableColumn<OfficerRecord, String> colln;
    @FXML
    private TableColumn<OfficerRecord, String> colCompany;
    @FXML
    private Button btnLoadBatch;
    @FXML
    private Button btnAssign;
    @FXML
    private ChoiceBox<String> cbUser;
    @FXML
    private Label lbAssignedTo;
    @FXML
    private Label lbBatch;
    @FXML
    private Label lbCreatedOn;
    @FXML
    private Label lbDate;
    private String tfTName;
    private String tfTLocation;
    private String tfTBatch;
    private String tfTDate;
    public static final Pattern VALID_ALPHA_NUMERIC_REGEX = Pattern.compile("[a-zA-Z0-9]*");

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
    void saveBtnClick(ActionEvent event){
        System.out.println("Save Click!!");
        if (event.getSource() == this.btnSave) {
            cbBatch.getItems().clear();
            this.insertRecord();
            setBatchChoice();
            reloadTable();
        }
    }

    @FXML
    void setBtnAssign(ActionEvent event){
        if (event.getSource() == this.btnAssign) {
            showDate();
            String[] names = cbUser.getValue().split(" ");
            String firstName = names[0];
            String lastName = names[1];
            int userId = (searchUser(firstName, lastName).getId());
            assignBatch(userId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Instructor Assigned");
            alert.setHeaderText(""+firstName+" "+lastName+" Was assigned to Batch: "+cbBatch.getValue());
            alert.showAndWait();
            //setCellValue();
        }
    }

    public String intToString(int number){
        return Integer.toString(number);
    }

    @FXML
    void setBtnLoadBatch(ActionEvent event){
        if (event.getSource() == this.btnLoadBatch) {
            System.out.println("Yeas");
            String batch = cbBatch.getValue();
            batchTable(batch);
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
        //check if fields are empty
        if(tfTBatch.isEmpty() || tfTName.isEmpty() || tfTLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
            //check if fields match the regex
        }else if (!tfTBatch.matches(String.valueOf(VALID_ALPHA_NUMERIC_REGEX)) || !tfTName.matches("[a-zA-Z]+") || !tfTLocation.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter valid data");
            alert.showAndWait();
        }else{
            int userId = 1;
            String createdDate = getDateTime();
            String query = "INSERT INTO batch VALUES('" + tfTBatch + "','" + tfTName + "','" + tfTLocation + "','" + tfTrainingDate.getValue() + "','" + userId + "','" + createdDate + "')";
            reloadTable();
            executeQuery(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Batch Added");
            alert.setHeaderText("Batch: " + tfTBatch + " was added");
            alert.showAndWait();
            clearCell();
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

    private void clearCell(){
        tfBatchNumber.clear();
        tfTrainingName.clear();
        tfTrainingLocation.clear();
    }
    ////////////////////////////////// BATCH TABLE POPULATION///////////////////////////////////////////////////////////
    ObservableList<Batch> oblist = FXCollections.observableArrayList();
    ArrayList<String> batchChoice = new ArrayList<String>();
    ArrayList<String> userChoice= new ArrayList<String>();
    ObservableList<OfficerRecord> reclist = FXCollections.observableArrayList();
    ObservableList<User> ulist = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        setBatchChoice();
        reloadTable();
        setUserChoice();
        setUserList();
        setCellValue();
        batchTable("bc1003");
    }

    public void reloadTable(){
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
    public void batchTable(String batch){
        officerTv.getItems().clear();
        Connection conn3 = getConnection();
        try {
            ResultSet result = conn3.createStatement().executeQuery("select * from guardsdb where batch_id = '"+batch+"'");
            while(result.next()){
                reclist.add(new OfficerRecord(result.getInt("id"),result.getString("fname"),
                        result.getString("lname"),result.getString("company"),
                        result.getString("contact"),result.getDate("medical_exp"),
                        result.getDate("psra_exp"),result.getDate("police_rec_exp"),result.getString("batch_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBatchID.setCellValueFactory(new PropertyValueFactory<>("batchid"));
        colfn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colln.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("Company"));
        officerTv.setItems(reclist);
    }
    public void setBatchChoice(){
        batchChoice.clear();
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
        cbBatch.setValue("bc1003");
        cbBatch.getItems().addAll(batchChoice);
    }
    //alpha numeric regex


    public void setUserChoice(){
        userChoice.clear();
        Connection conn2 = getConnection();
        try {
            ResultSet result = conn2.createStatement().executeQuery("select * from users");
            while (result.next()) {
                userChoice.add(""+result.getString("fname")+" "+result.getString("lname"));
                System.out.println(userChoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cbUser.setValue("Default");
        cbUser.getItems().addAll(userChoice);
    }
    //Select from user table and create a list of users
    public void setUserList(){
        ulist.clear();
        Connection conn = getConnection();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from users");
            while (result.next()) {
                ulist.add(new User(result.getInt("id"),result.getString("fname"),result.getString("lname"),
                        result.getString("email"),result.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User searchUser(String fn, String ln){
        for(User u: ulist){
            if(u.getFname().equals(fn) && u.getLname().equals(ln)){
                return u;
            }
        }
        return null;
    }

    public void setCellValue(){
        BatchViews.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Batch batchData = BatchViews.getItems().get(BatchViews.getSelectionModel().getSelectedIndex());
                tfBatchNumber.setText(batchData.getBatchNumber());
                cbBatch.setValue(batchData.getBatchName());
                tfTrainingName.setText(batchData.getBatchName());
                tfTrainingLocation.setText(batchData.getLocation());
                lbBatch.setText(batchData.getBatchNumber());
                lbCreatedOn.setText(getDate(getCreated(batchData.getBatchNumber())));
                int userId = (getUserId(batchData.getBatchNumber()));
                User user = searchUserId(userId);
                String Username = ""+user.getFname()+" "+user.getLname();
                lbAssignedTo.setText(Username);
                lbDate.setText(getDate(batchData.getTrainingDate()));
            }
        });
    }
    private void assignBatch(int uID){
        Connection conn = getConnection();
        String batchNum,tname,tlocation;
        batchNum = tfBatchNumber.getText();
        tname = tfTrainingName.getText();
        tlocation = tfTrainingLocation.getText();
        String query = "UPDATE batch SET batch_id = '"+batchNum+"', t_name = '"+tname+"', location = '"+tlocation+"',user_id = '"+uID+"' WHERE batch_id = '"+batchNum+"'";
        executeQuery(query);
    }
    public void displayDetails(){

    }
    public Date getCreated(String id){
        Connection conn = getConnection();
        Date created = null;

        try {
            ResultSet result = conn.createStatement().executeQuery("select * from batch where batch_id = '"+id+"'");
            while (result.next()) {
                created = result.getDate("created_on");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (created);
    }

    public int getUserId(String id){
        Connection conn = getConnection();
        // Selete created date from database based on id
        int uId = 0;
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from batch where batch_id = '"+id+"'");
            while (result.next()) {
                uId = result.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (uId);
    }

    public String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    //search user by id
    public User searchUserId(int id){
        for(User u: ulist){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
    public Date showDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return date;
    }
    //Date time
    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }





}
