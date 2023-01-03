/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobil;

import static Mobil.MobilAllList.tblMobil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static config.Config.getKoneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.RowFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
        model.addColumn("ID");
        TableColumn tc = tblMobil.getColumnModel().getColumn(model.getColumnCount() - 1);
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
            String sql = "Select * from mobil";
            ResultSet res = stat.executeQuery(sql);

            //penelusuran baris pada tabel tblGaji dari database
            while (res.next()) {
                Object[] obj = new Object[7];

                obj[0] = res.getString("nomor_plat");
                obj[1] = res.getString("merk");
                obj[2] = res.getDate("tahun_pembuatan");
                obj[3] = res.getString("warna");
                obj[4] = res.getInt("stok");
                obj[5] = res.getString("harga_sewa_perhari");
                obj[6] = res.getInt("ID");

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

//    untuk menampilkan mobil yang tersedia method di panggil di method list mobil tersedia 
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

//    menambah data mobil
    void insertData(String nomorPlat, String merk, Date tahunPembuatan, String warna, int stok, String hargaSewaPerhari) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();
            String query = "SELECT * FROM mobil WHERE nomor_plat='" + nomorPlat + "'";
            ResultSet res = stat.executeQuery(query);
            if (res.next()) {
                JOptionPane.showMessageDialog(null, "Nomor plat sudah terdaftar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
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

    void editData(String nomorPlat, String merk, Date tahunPembuatan, String warna, int stok, String hargaSewaPerhari, int id) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            long time = tahunPembuatan.getTime();
            java.sql.Date tahunPembuatanSql = new java.sql.Date(time);
            // Buat statement

            String sql = "UPDATE mobil set nomor_plat = ? , merk = ? , tahun_pembuatan = ?,warna = ?, stok = ? , harga_sewa_perhari = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, nomorPlat);
            stmt.setString(2, merk);
            stmt.setDate(3, tahunPembuatanSql);
            stmt.setString(4, warna);
            stmt.setInt(5, stok);
            stmt.setString(6, hargaSewaPerhari);
            stmt.setInt(7, id);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listMobil();
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
            String sql = "delete from mobil where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setInt(1, id);

            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listMobil();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Data gagal hapus ", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void totalMobil() {
        ResultSet res = null;
        try {
            conn = getKoneksi();
            stat = conn.createStatement();
            String sql = "SELECT SUM(stok) AS total_stok FROM mobil";
            res = stat.executeQuery(sql);
            if (res.next()) {
                int jumlahMobil = res.getInt("total_stok");
                MobilAllList.jJumlahMobil.setText(String.valueOf(jumlahMobil));
            } else {
                MobilAllList.jJumlahMobil.setText("0");
            }
        } catch (Exception e) {
            MobilAllList.jJumlahMobil.setText("Error");
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

//    menampilkan seluruh list mobil
    void listMobil() {
        String val = "All";
        clearList(val);
        getDataAll();
        totalMobil();
    }

//    menampilan list mobil yang tersedia
    void listMobilTersedia() {
        String val = "";
        clearList(val);
        getMobilTersedia();
    }

//    method search
    void search(String query) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblMobil.getModel());
        tblMobil.setRowSorter(sorter);

        // Buat filter berdasarkan teks yang dimasukkan
        String text = query;
        RowFilter<TableModel, Object> filter = RowFilter.regexFilter("(?i)" + text, 1);

        // Set filter ke tabel
        sorter.setRowFilter(filter);
    }
}
