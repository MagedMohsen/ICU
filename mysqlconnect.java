package main;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb(){
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid-19","root","");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 
    }

}