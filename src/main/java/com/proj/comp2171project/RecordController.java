package com.proj.comp2171project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RecordController {
    @FXML
    ImageView imageView = new ImageView();
    List<String> lstFile;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnPolRec;
    @FXML
    private Button btnMed;
    @FXML
    private Button btnPSRA;
    @FXML
    private TextField labeltxt;
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
    private DatePicker medicalDate;
    @FXML
    private DatePicker polRecDate;
    @FXML
    private DatePicker psraDate;
    @FXML
    private TextField tfContact;
    @FXML
    private TextField tfMedical;
    @FXML
    private TextField tfPSRA;
    @FXML
    private TextField tfPolR;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int id;
    private String fname;
    private String lname;
    private String company;
    private String contact;
    private String medicalPath;
    private String psraPath;
    private String policePath;

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
    void onHelloButtonClick(ActionEvent event) {
        System.out.println("You Clicked ME!!");
        if (event.getSource() == btnSave) {
            //check if all fields are filled
            if (tfFname.getText().isEmpty() || tfLname.getText().isEmpty() || tfCompany.getText().isEmpty() || tfContact.getText().isEmpty() || tfid.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            }else if (medicalDate.getValue() == null || polRecDate.getValue() == null || psraDate.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
                //check if fields are filled correctly
            } else  {
            insertRecord();
            //confirm record is saved
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Information");
            alert.setContentText("Record Saved");
            alert.showAndWait();
            }
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
        imageView.setImage(img);
    }
    @FXML
    void saveFileBtnClick(ActionEvent event){
        if (event.getSource()== btnMed) {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File f = fc.showOpenDialog(null);
            if (f != null) {
                tfMedical.setText(f.getAbsolutePath());
            }
        }else if (event.getSource()== btnPSRA){
            FileChooser fcPsra = new FileChooser();
            fcPsra.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files","*.pdf"));
            File f = fcPsra.showOpenDialog(null);
            if (f != null){
                tfPSRA.setText( f.getAbsolutePath());
            }
        } else if (event.getSource()== btnPolRec){
            FileChooser fcPol = new FileChooser();
            fcPol.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files","*.pdf"));
            File f = fcPol.showOpenDialog(null);
            if (f != null){
                tfPolR.setText( f.getAbsolutePath());
             }
        }
    }

    public void initialize(URL url,ResourceBundle rb){
        lstFile = new ArrayList<>();
        lstFile.add("*.doc");
        lstFile.add("*.docx");
        lstFile.add("*.DOC");
        lstFile.add("*.DOCX");
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
        id = Integer.parseInt(tfid.getText());
        fname = tfFname.getText();
        lname = tfLname.getText();
        company = tfCompany.getText();
        contact = tfContact.getText();
        medicalPath = tfMedical.getText();
        policePath = tfPolR.getText();
        psraPath = tfPSRA.getText();
        String batchId = "000000";
        String query = "INSERT INTO guardsdb VALUES (" + id + ",'" + fname + "','" + lname +"','"
                        + company +"','" + contact +"','" + medicalDate.getValue() +"','"
                + psraDate.getValue() +"','" + polRecDate.getValue() +"','"+ batchId +"')";
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
        tfid.clear();
        tfFname.clear();
        tfLname.clear();
        tfCompany.clear();
        tfContact.clear();
        tfPolR.clear();
        tfPSRA.clear();
        tfMedical.clear();
    }

}
