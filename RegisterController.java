package main;


import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RegisterController {
    
    
    @FXML
    private TextField SSNP;

    @FXML
    private TextField NameP;

    @FXML
    private TextField AgeP;

    @FXML
    private ComboBox GenderP;

    @FXML
    private TextField EmailP;

    @FXML
    private PasswordField PasswordP1;

    @FXML
    private PasswordField PasswordP2;

    @FXML
    private Button RegP;
    
    @FXML
    private Button LogP;

    @FXML
    private TextField SSND;

    @FXML
    private TextField NameD;

    @FXML
    private TextField SciDgre;

    @FXML
    private ComboBox GenderD;
    
    @FXML
    private ComboBox Hospital;
    
    @FXML
    private TextField EmailD;

    @FXML
    private PasswordField PasswordD1;

    @FXML
    private PasswordField PasswordD2;

    @FXML
    private Button RegD;
    
    @FXML
    private Button LogD;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst ;
        
    @FXML
    void RegD(ActionEvent event) throws Exception{
        
        conn = mysqlconnect.ConnectDb();
        pst = conn.prepareStatement("SELECT HOSID FROM `hospital` WHERE NAME = ? ");
        pst.setString(1,Hospital.getValue().toString());
        rs = pst.executeQuery();
        int ID=0;
        if (rs.next()){
        ID = rs.getInt(1);}
        String G;
        if (GenderD.getValue().toString()== "Male")
        {
            G = "M";
        }
        else
        {
            G = "F";
        }
        pst = conn.prepareStatement("INSERT INTO `doctor` (`SSN`, `NAME`, `SCIDGRE`, `GENDER`, `HOSID`, `EMAIL`, `PASS`) VALUES ( ?, ?, ?, ?, ?, ?, ?)");
        try {
//        if (PasswordD1 == PasswordD2 && EmailD.toString().contains(".com"))
//            {
            pst.setString(1, SSND.getText());
            pst.setString(2, NameD.getText());
            pst.setString(3, SciDgre.getText());
            pst.setString(4, G);
            pst.setInt(5, ID);
            pst.setString(6, EmailD.getText());
            pst.setString(7, PasswordD1.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Saved");
//            }
//        else
//            JOptionPane.showMessageDialog(null, "Invalid Data");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e );}
    }
    @FXML
    void RegP(ActionEvent event) throws Exception{
        conn = mysqlconnect.ConnectDb();
        
        String G;
        if (GenderP.getValue().toString()== "Male")
        {
            G = "M";
        }
        else
        {
            G = "F";
        }
        int age;
        age = Integer.parseInt(AgeP.getText());
        pst = conn.prepareStatement("INSERT INTO `patient` (`SSN`, `NAME`, `AGE`, `GENDER`, `EMAIL`, `PASS`) VALUES ( ?, ?, ?, ?, ?, ?)");
        try {
//        if (PasswordD1 == PasswordD2 && EmailD.toString().contains(".com"))
//            {
            pst.setString(1, SSNP.getText());
            pst.setString(2, NameP.getText());
            pst.setString(4, G);
            pst.setInt(3, age);
            pst.setString(5, EmailP.getText());
            pst.setString(6, PasswordP1.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Saved");
//            }
//        else
//            JOptionPane.showMessageDialog(null, "Invalid Data");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e );}

    }
    
    @FXML
    void LogD(ActionEvent event) {
        try{
                LogD.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("UI.fxml"));
                Stage mainStage = new Stage();
                Scene scene;
                scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }

    @FXML
    void LogP(ActionEvent event) {
        try{
                LogP.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("UI.fxml"));
                Stage mainStage = new Stage();
                Scene scene;
                scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }

    
    @FXML
    public void initialize(){
        GenderD.getItems().addAll("Male","Female");
        GenderP.getItems().addAll("Male","Female");
        
        List<String> Hospitals = new ArrayList<>();
        List<String> HospitalIDList = new ArrayList<>();
        conn = mysqlconnect.ConnectDb();
        Statement stmt;
        
        String sql0 = "SELECT NAME, HOSID FROM HOSPITAL";
        try{
                stmt = conn.createStatement();
                ResultSet rst =  stmt.executeQuery(sql0);
                while(rst.next()) {
                String current = rst.getString("NAME");
                Hospitals.add(current);
                int current2 = rst.getInt("HOSID");
                HospitalIDList.add(String.valueOf(current2));
                }
        } catch (Exception e){
                JOptionPane.showMessageDialog(null,e );
            }
        
        ObservableList<String> ListofHospitals = FXCollections.observableArrayList(Hospitals);
        ObservableList ListofHospitalsID = FXCollections.observableArrayList(HospitalIDList);
        Hospital.setItems(ListofHospitals);
        Hospital.setValue(ListofHospitalsID);
        
        
    }
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }
}
