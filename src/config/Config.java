/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Config {
    private static Connection conn;
   
    
    
     public static Connection getKoneksi() {
        String host = "jdbc:mysql://localhost/rental_mobil",
                user = "root",
                pass = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(host, user, pass);
        } catch (SQLException | ClassNotFoundException err) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal", "Eroor", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }

}
