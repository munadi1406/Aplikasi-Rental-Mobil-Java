/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobil;

import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static config.Config.getKoneksi;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JTextField;

/**
 *
 * @author Asus
 */
public class MobilClass {

    private Statement stat;
    private DefaultTableModel model;
    private static Connection conn;

    public void clearList(String val) {
        model = new DefaultTableModel();

        JTable tblMobil;
        if (val.equals("All")) {
            tblMobil = MobilAllList.tblMobil;
        } else {
            tblMobil = Mobil.tblMobil;
        }

        tblMobil.setModel(model);
        model.addColumn("Nomot Plat");
        model.addColumn("Merk");
        model.addColumn("tahun pembuatan");
        model.addColumn("warna");
        model.addColumn("stok");
        model.addColumn("Harga Sewa");

    }

//    unutk menampilkan list seluruh mobil yang tersedia
    void getDataAll() {

        //menghapus isi table tblGaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            //membuat statemen pemanggilan data pada table tblGaji dari database
            conn = getKoneksi();
            stat = conn.createStatement();
            String sql = "Select * from mobil";
            ResultSet res = stat.executeQuery(sql);

            //penelusuran baris pada tabel tblGaji dari database
            while (res.next()) {
                Object[] obj = new Object[6];
                obj[0] = res.getString("nomor_plat");
                obj[1] = res.getString("merk");
                obj[2] = res.getDate("tahun_pembuatan");
                obj[3] = res.getString("warna");
                obj[4] = res.getInt("stok");
                obj[5] = res.getString("harga_sewa_perhari");

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

//    untuk menampilkan mobil yang tersedia
    void getMobilTersedia() {

        //menghapus isi table tblGaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            //membuat statemen pemanggilan data pada table tblGaji dari database
            conn = getKoneksi();
            stat = conn.createStatement();
            String sql = "Select * from mobil where stok > 0";
            ResultSet res = stat.executeQuery(sql);

            //penelusuran baris pada tabel tblGaji dari database
            while (res.next()) {
                Object[] obj = new Object[6];
                obj[0] = res.getString("nomor_plat");
                obj[1] = res.getString("merk");
                obj[2] = res.getDate("tahun_pembuatan");
                obj[3] = res.getString("warna");
                obj[4] = res.getInt("stok");
                obj[5] = res.getString("harga_sewa_perhari");

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

    void insertData(String nomorPlat, String merk, Date tahunPembuatan, String warna, int stok, String hargaSewaPerhari) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            long time = tahunPembuatan.getTime();
            java.sql.Date tahunPembuatanSql = new java.sql.Date(time);
            // Buat statement
            String sql = "INSERT INTO mobil (nomor_plat, merk, tahun_pembuatan, warna, stok, harga_sewa_perhari) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, nomorPlat);
            stmt.setString(2, merk);
            stmt.setDate(3, tahunPembuatanSql);
            stmt.setString(4, warna);
            stmt.setInt(5, stok);
            stmt.setString(6, hargaSewaPerhari);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listMobil();
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

    void listMobil() {
        String val = "All";
        clearList(val);
        getDataAll();
    }

    void listMobilTersedia() {
        String val = "";
        clearList(val);
        getMobilTersedia();
    }
//    fungsi mencari
}
