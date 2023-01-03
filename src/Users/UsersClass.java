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

//    unutk menampilkan list seluruh mobil yang tersedia ,di panggil  di listmobil
    void getDataAll() {
        //menghapus isi table tblGaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            //membuat statemen pemanggilan data pada table tblGaji dari database
            conn = getKoneksi();
            stat = conn.createStatement();
            String sql = "Select * from users";
            ResultSet res = stat.executeQuery(sql);

            //penelusuran baris pada tabel tblGaji dari database
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
                    // menangani kesalahan jika terjadi
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // menangani kesalahan jika terjadi
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

                // Konversi hash ke dalam bentuk hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                password = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Buat statement
            String sql = "INSERT INTO users (username,password,role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Data gagal disimpan ke database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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
            // Atur parameter untuk statement
            stmt.setString(1, username);
            stmt.setString(2, role);
            stmt.setInt(3, id);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Data gagal ubah ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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

                // Konversi hash ke dalam bentuk hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                password = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Buat statement
            String sql = "UPDATE users set password = ?  where id_users = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, password);
            stmt.setInt(2, id);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Data gagal ubah ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    void hapusData(int id) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            // Buat statement
            String sql = "delete from users where id_users = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setInt(1, id);

            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Users berhasil di hapus ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listUsers();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Users gagal hapus ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup koneksi
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void listUsers() {
        clearList();
        getDataAll();
//        totalMobil();
    }

}
