/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.Config;

/**
 *
 * @author Asus
 */
public class LoginClass {

// ...
    private static String role;
    private static int id_users;

    public boolean login(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Koneksi ke database
            conn = Config.getKoneksi();

            // enkripsi password
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            String enkripsi = new BigInteger(1, hash).toString(16);

            String sql = "SELECT *, role FROM users WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, enkripsi);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                id_users = res.getInt("id_users");
                role = res.getString("role");

                return true;
            } else {
                return false;
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {

                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
    
                }
            }
        }
    }

//    untuk mengirim role ke home (hasil dari login)
    public static String getRole() {
        return role;
    }

//    mengirim id users ke form transaksi (hasil dari login)
    public static int getIdUsers() {
        return id_users;
    }

}
