package main;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Maged Mohsen Saafan
 */
public class UIController implements Initializable {
    
    @FXML
    private TextField Email;
    
    @FXML
    private PasswordField Pass;
    
    @FXML
    private Button Login_btn;
    
    @FXML
    private Button Reg;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public static String PATID_SIGNED;
    
    @FXML
    private void login(ActionEvent event) throws Exception{
        
        
        conn = mysqlconnect.ConnectDb();
        String sql = "SELECT * FROM PATIENT WHERE EMAIL = ? AND PASS = ? ";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Email.getText());
            pst.setString(2, Pass.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                PATID_SIGNED=rs.getString(1);
                JOptionPane.showMessageDialog(null, "Login Successful");
                
                Login_btn.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Stage mainStage = new Stage();
                Scene scene;
                scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
            
            else
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e );
        }

    }
    
    @FXML
    private void Reg(ActionEvent event) throws Exception{
        
        Reg.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage mainStage = new Stage();
        Scene scene;
        scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
