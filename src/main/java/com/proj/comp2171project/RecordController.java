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
    private Button uploadBtn;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    @FXML
    void saveFileBtnClick(ActionEvent event){
        if (event.getSource()== uploadBtn){
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files","*.pdf"));
            File f = fc.showOpenDialog(null);
            if (f != null){
                labeltxt.setText("Selected File:" + f.getAbsolutePath());
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
        String query = "INSERT INTO records VALUES (" + tfid.getText() + ",'" + tfFname.getText() + "','" + tfLname.getText() + "','" + tfCompany.getText() +"','" + tfCompany.getText() +"')";
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
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(MainDriver.class.getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //Scene scene = new Scene(fxmlLoader.load(), 950, 600); My method
        scene = new Scene(root, 950, 600);
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