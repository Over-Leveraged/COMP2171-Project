package com.proj.comp2171project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;




public class ManagementController implements Initializable {

    @FXML
    private TableColumn<Recordss,String> colCom;

    @FXML
    private TextField companyTF;

    @FXML
    private TableColumn<Recordss,String> colCon;

    @FXML
    private TableColumn<Recordss, Date> colMed;

    @FXML
    private TableColumn<Recordss, Date> colPSRA;

    @FXML
    private TableColumn<Recordss, Date> colPolice;

    @FXML
    private TextField conTF;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Recordss,String> colFn;

    @FXML
    private TextField fnTF;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableColumn<Recordss,Integer> colId;

    @FXML
    private TextField idTF;

    @FXML
    private Button btnExport;

    @FXML
    private Button insertBtn;

    @FXML
    private Button btnRefresh;

    @FXML
    private TableColumn<Recordss,String> colLn;

    @FXML
    private TextField lnTF;

    @FXML
    private TableView<Recordss> recordView;

    @FXML
    private Button updateBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileInputStream fileInputStream;

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
                header.createCell(0).setCellValue("id");
                header.createCell(1).setCellValue("fname");
                header.createCell(2).setCellValue("lname");
                header.createCell(3).setCellValue("company");
                header.createCell(4).setCellValue("contact");

                int index = 1;
                while(rs.next()){
                    XSSFRow row = sheet.createRow(index);
                    row.createCell(0).setCellValue(rs.getString("id"));
                    row.createCell(1).setCellValue(rs.getString("fname"));
                    row.createCell(2).setCellValue(rs.getString("lname"));
                    row.createCell(3).setCellValue(rs.getString("company"));
                    row.createCell(4).setCellValue(rs.getString("contact"));
                    index++;
                }

                FileOutputStream fileOutputStream = new FileOutputStream("OfficerDetails.xlsx");
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
        String firstname,lastname,company,contact;
        reloadtable();
        firstname = fnTF.getText();
        lastname = lnTF.getText();
        company = companyTF.getText();
        contact = conTF.getText();
        String query = "UPDATE guardsdb SET fname = '"+firstname+"', lname ='"+ lastname +"', company ='"+ company +"', contact ='"+ contact +"' where id = '"+idTF.getText()+"' ";
        executeQuery(query);
        reloadtable();
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        setCellValue();
    }

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 950, 710);
        scene.getStylesheets().add("theme.css");
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");
    }

    @FXML
    public void switchToEdit(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("RecordManagement.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root2, 990, 710);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");

    }

    @FXML
    public void switchToNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RecordUi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");
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
        setCellValue();
    }

    private void setCellValue(){
        recordView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Recordss mt = recordView.getItems().get(recordView.getSelectionModel().getSelectedIndex());
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
        FilteredList<Recordss> filteredData = new FilteredList<>(oblist, b -> true);
        tfSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(recordss -> {
                if (newValue== null || newValue.isEmpty()){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (recordss.getFirstname().toLowerCase().contains(searchKeyword)){
                    return true;
                }else if(recordss.getLastname().toLowerCase().contains(searchKeyword)){
                    return true;
                }else return recordss.getCompany().toLowerCase().contains(searchKeyword);
            });
        });

        SortedList<Recordss> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(recordView.comparatorProperty());
        recordView.setItems(sortedList);
    }
}


