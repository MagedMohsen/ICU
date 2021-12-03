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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class MainController implements Initializable {

    @FXML
    private ChoiceBox City;

    @FXML
    private Button exc_btn;
    
    @FXML
    private Button bookbtn;

    @FXML
    private Pane HosPane;

    @FXML
    private Label HosName;

    @FXML
    private Label ICU;

    @FXML
    private Label Vent;

    @FXML
    private Label Address;
    
    @FXML
    public static Button DailyRec;
    
    @FXML
    private Button Logout;
    
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void exc(ActionEvent event) {
        
        conn = mysqlconnect.ConnectDb();
        String sql = "SELECT `NAME`,`ICUBEDS`,`VENT`,`ADDRESS` FROM `hospital` WHERE CITY = ?";
        try{
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, City.getValue().toString());
            rs = pst.executeQuery();
            if (rs.next()){
                bookbtn.setVisible(true);
                HosName.setText(rs.getString(1));
                ICU.setText(rs.getString(2));
                Vent.setText(rs.getString(3));
                Address.setText(rs.getString(4));
                bookbtn.setVisible(true);
            }
            int ICUValue;
            ICUValue = Integer.parseInt(ICU.getText());
            if (ICUValue < 1)
                bookbtn.setDisable(true);
            else
                bookbtn.setDisable(false);
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }
    
    @FXML
    void book(ActionEvent event) {
        String PAT_ID = UIController.PATID_SIGNED;
        int HOSID_BOOK=0; 
        conn = mysqlconnect.ConnectDb();
        String sql = "SELECT HOSID FROM HOSPITAL WHERE CITY = ?";
        try{
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, City.getValue().toString());
            rs = pst.executeQuery();
            if (rs.next()){
                HOSID_BOOK = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Hospital ID memorized = "+HOSID_BOOK);
            }
            int BID_CURR = 0;
            
            String sql1 = "SELECT BID FROM BED WHERE HOSID = "+HOSID_BOOK+" ORDER BY BID DESC";
            try{
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();
                if (rs.next()){
                    BID_CURR = 1+rs.getInt(1);
                    JOptionPane.showMessageDialog(null, "new Bed ID created = "+BID_CURR);
                }
                else {
                    BID_CURR = 1;
                    JOptionPane.showMessageDialog(null, "First Bed Id Created = "+BID_CURR);
                }
            } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }
            
            String sql2 ="INSERT INTO BED (HOSID,PATID, BID, DATEOFRES) VALUES (?, ?, ?, NOW())";
            pst = conn.prepareStatement(sql2);
            try{
                pst.setInt(1, HOSID_BOOK);
                pst.setString(2, PAT_ID);
                pst.setInt(3, BID_CURR);
                pst.execute();
               
//            rs = pst.executeQuery();
//            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Booking Successful = "+PAT_ID);
//            }
//            else
//                JOptionPane.showMessageDialog(null, "Already booked");
            } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }
    
    @FXML
    void Logout(ActionEvent event) {
        try{
                Logout.getScene().getWindow().hide();
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
    void DailyRec(ActionEvent event) {
        try{
                Parent root;
                root = FXMLLoader.load(getClass().getResource("DailyRec.fxml"));
                Stage mainStage = new Stage();
                Scene scene;
                scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookbtn.setVisible(false);
        HosName.setText("");
        ICU.setText("");
        Vent.setText("");
        Address.setText("");
        
        List<String> Cities = new ArrayList<>();
        conn = mysqlconnect.ConnectDb();
        Statement stmt;
        
        String sql0 = "SELECT CITY FROM HOSPITAL";
        try{
                stmt = conn.createStatement();
                ResultSet rst =  stmt.executeQuery(sql0);
                while(rst.next()) {
                String current = rst.getString("CITY");
                Cities.add(current);
                }
        } catch (Exception e){
                JOptionPane.showMessageDialog(null,e );
            }
        
        ObservableList<String> ListofCities = FXCollections.observableArrayList(Cities);
        City.setItems(ListofCities);
    }   
    
}
