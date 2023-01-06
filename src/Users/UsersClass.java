/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;


import static config.Config.getKoneksi;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Asus
 */
public class UsersClass {

    private Statement stat;
    private static Connection conn;
    private DefaultTableModel model;

    public void clearList() {
        model = new DefaultTableModel();
        JTable tblUsers;

        tblUsers = Users.jTableUsers;

        tblUsers.setModel(model);
        model.addColumn("Username");
        model.addColumn("Passowrd");
        model.addColumn("Role");
        model.addColumn("id_users");
        TableColumn tc = tblUsers.getColumnModel().getColumn(model.getColumnCount() - 1);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
    }

//menampilkan list users
    void getDataAll() {
        //menghapus isi table tblGaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
    
            conn = getKoneksi();
            stat = conn.createStatement();
            String sql = "Select * from users";
            ResultSet res = stat.executeQuery(sql);

         
            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getString("username");
                obj[1] = res.getString("password");
                obj[2] = res.getString("role");
                obj[3] = res.getInt("id_users");
                model.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        } finally {
            // menutup koneksi ke database
            if (stat != null) {
                try {
                    stat.close();
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

    void insertData(String username, String password, String role) throws NoSuchAlgorithmException {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();
            String query = "SELECT * FROM users WHERE username ='" + username + "'";
            ResultSet res = stat.executeQuery(query);
            if (res.next()) {
                JOptionPane.showMessageDialog(null, "Username tidak tersedia", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

                // Konversi hash ke dalam bentuk hexa
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                password = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

       
            String sql = "INSERT INTO users (username,password,role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
           
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
         
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
              
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
             
                JOptionPane.showMessageDialog(null, "Data gagal disimpan ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, "Data Gagal di Simpan", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
             
                }
            }
        }
    }

    void editData(String username, String role, int id) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            // Buat statement
            String sql = "UPDATE users set username = ? , role = ? where id_users = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
      
            stmt.setString(1, username);
            stmt.setString(2, role);
            stmt.setInt(3, id);
  
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
       
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
                
                JOptionPane.showMessageDialog(null, "Data gagal ubah ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Data gagal di ubah", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                  
                }
            }
        }
    }

    void ubahPassword(String password, String password2, int id) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            if (!password.equals(password2)) {
                JOptionPane.showMessageDialog(null, "Password yang dimasukkan tidak sama", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

                // Konversi hash ke dalam bentuk hexa
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                password = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            String sql = "UPDATE users set password = ?  where id_users = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
       
            stmt.setString(1, password);
            stmt.setInt(2, id);
        
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
               
                JOptionPane.showMessageDialog(null, "Password berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
               
                JOptionPane.showMessageDialog(null, "Password gagal ubah ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, "Gagal Ubah Password", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    
                }
            }
        }
    }
    
    
    void hapusData(int id) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

      
            String sql = "delete from users where id_users = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
         
            stmt.setInt(1, id);

            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                
                JOptionPane.showMessageDialog(null, "Users berhasil di hapus ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
             
                JOptionPane.showMessageDialog(null, "Users gagal hapus ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
          
            JOptionPane.showMessageDialog(null, "Gagal Menghapus User", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                
                }
            }
        }
    }

//    menampilkan list users
    void listUsers() {
        clearList();
        getDataAll();
    }

}
