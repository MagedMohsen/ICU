/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Maged Mohsen Saafan
 */
public class DailyRecController implements Initializable {

    @FXML
    private Button Logout1;

    @FXML
    private Button exc_btn_dly;

    @FXML
    private Pane HosPane;

    @FXML
    private Label Pos;

    @FXML
    private Label Rec;

    @FXML
    private Label Dead;

    @FXML
    private Label TPos;

    @FXML
    private Label TRec;

    @FXML
    private Label TDed;

    @FXML
    private Label Active;

    @FXML
    private ChoiceBox Day;

    @FXML
    private Button Today;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void Logout(ActionEvent event) {
        try{
                Logout1.getScene().getWindow().hide();
                MainController.DailyRec.getScene().getWindow().hide();
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
    void Today(ActionEvent event) {
        
        conn = mysqlconnect.ConnectDb();
        String sql6 = "SELECT `POSITIVE`,`RECOVERED`,`DEATH` FROM `daily_record` WHERE `RDATE` = CURRENT_DATE()";
        try{
            
            pst = conn.prepareStatement(sql6);
            rs = pst.executeQuery();
            if (rs.next()){
                
                Pos.setText(Integer.toString(rs.getInt(1)));
                Rec.setText(Integer.toString(rs.getInt(2)));
                Dead.setText(Integer.toString(rs.getInt(3)));
                
                
            }
            
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }

    @FXML
    void exc_dly(ActionEvent event) {
        
        conn = mysqlconnect.ConnectDb();
        String sql5 = "SELECT `POSITIVE`,`RECOVERED`,`DEATH` FROM `daily_record` WHERE `RDATE` = ? ";
        try{
            String Day_Var = "'"+Day.getValue().toString()+"'";
            pst = conn.prepareStatement(sql5);
            pst.setString(1, Day_Var);
            rs = pst.executeQuery();
            
            if (rs.next()){
                
                Pos.setText(Integer.toString(rs.getInt(1)));
                Rec.setText(Integer.toString(rs.getInt(2)));
                Dead.setText(Integer.toString(rs.getInt(3)));
                JOptionPane.showMessageDialog(null, Day_Var);
                
            }
            else
                JOptionPane.showMessageDialog(null,"Opertaion Failed" );
            
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = mysqlconnect.ConnectDb();
        Statement stmt;
        List<String> Days = new ArrayList<>();
        
        String sql0 = "SELECT RDATE FROM DAILY_RECORD";
        try{
                stmt = conn.createStatement();
                ResultSet rst =  stmt.executeQuery(sql0);
                while(rst.next()) {
                String current = rst.getString("RDATE");
                Days.add(current);
                }
        } catch (Exception e){
                JOptionPane.showMessageDialog(null,e );
            }
        
        
        ObservableList<String> ListofDays = FXCollections.observableArrayList(Days);
        Day.setItems(ListofDays);
    }    
    
}
