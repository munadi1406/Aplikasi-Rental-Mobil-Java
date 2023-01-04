/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelanggan;

import static config.Config.getKoneksi;
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
public class PelangganClass {
     private Statement stat;
    private static Connection conn;
    private DefaultTableModel model;

    
    
//    funsginya untuk set kolom di jTable
    public void clearList() {
        model = new DefaultTableModel();
        JTable tblPelanggan;

        tblPelanggan = Pelanggan.jTablePelanggan;

        tblPelanggan.setModel(model);
        model.addColumn("Nama");
        model.addColumn("No Telepon");
        model.addColumn("Alamat");
        model.addColumn("id_pelanggan");
        TableColumn tc = tblPelanggan.getColumnModel().getColumn(model.getColumnCount() - 1);
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
            String sql = "Select * from pelanggan";
            ResultSet res = stat.executeQuery(sql);

            //penelusuran baris pada tabel tblGaji dari database
            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getString("nama");
                obj[1] = res.getString("no_telepon");
                obj[2] = res.getString("alamat");
                obj[3] = res.getInt("id_pelanggan");
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

    void insertData(String nama, String noTelepon, String alamat) throws NoSuchAlgorithmException {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();
            String query = "SELECT * FROM pelanggan WHERE no_telepon ='" + noTelepon + "'";
            ResultSet res = stat.executeQuery(query);
            if (res.next()) {
                JOptionPane.showMessageDialog(null, "No Sudah Terdaftar ", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           

            // Buat statement
            String sql = "INSERT INTO pelanggan (nama,no_telepon,alamat) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, nama);
            stmt.setString(2, noTelepon);
            stmt.setString(3, alamat);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listPelanggan();
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

    void editData(String nama, String alamat, String noTelepon ,int id) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            // Buat statement
            String sql = "UPDATE pelanggan set nama = ? ,no_telepon = ? , alamat = ? where id_pelanggan = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setString(3,noTelepon);
            stmt.setInt(4, id);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listPelanggan();
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
            String sql = "delete from pelanggan where id_pelanggan = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setInt(1, id);

            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data Pelanggan berhasil di hapus ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listPelanggan();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Data Pelanggan gagal hapus ", "Error", JOptionPane.ERROR_MESSAGE);
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

    void listPelanggan() {
        clearList();
        getDataAll();
//        totalMobil();
    }
}
