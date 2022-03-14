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

   // private

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
        //lstFile.add("*.pdf");
    }

    //public void initialize(URL url, ResourceBundle rb) {
    //showRecords();

    //}

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
        //medExpiry = medicalDate;
        //System.out.println(medicalDate.getValue());
        String query = "INSERT INTO guardsdb VALUES (" + id + ",'" + fname + "','" + lname +"','" + company +"','" + contact +"','" + medicalDate.getValue()
                        +"','" + medicalPath +"','" + psraDate.getValue() +"','" + polRecDate.getValue() +"')";

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
    //ADD home button
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 990, 710);
        scene.getStylesheets().add("theme.css");
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");
    }
    @FXML
    public void switchToEdit(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("RecordManagement.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root2, 990, 710);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");

    }

    @FXML
    public void switchToNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //Scene scene = new Scene(fxmlLoader.load(), 950, 600); My method
        scene = new Scene(root, 990, 710);
        //stage.setScene();
        //scene.getStylesheets().add("theme.css");
        //stage.setTitle("GBD Dash");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        System.out.println("Test");

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