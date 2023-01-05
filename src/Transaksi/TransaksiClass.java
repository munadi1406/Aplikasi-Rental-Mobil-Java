/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import static config.Config.getKoneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Asus
 */
public class TransaksiClass {

    private Statement stat;
    private DefaultTableModel model;
    private static Connection conn;

    public void clearList() {
        model = new DefaultTableModel();

        JTable tblMobilTersedia;

        tblMobilTersedia = Transaksi.tblMobilTersedia;

        tblMobilTersedia.setModel(model);
        model.addColumn("Nomot Plat");
        model.addColumn("Merk");
        model.addColumn("tahun pembuatan");
        model.addColumn("warna");
        model.addColumn("stok");
        model.addColumn("Harga Sewa");
        model.addColumn("ID");
        TableColumn tc = tblMobilTersedia.getColumnModel().getColumn(model.getColumnCount() - 1);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);

    }

//    untuk menampilkan mobil yang tersedia method di panggil di method list mobil tersedia 
    void getMobilTersedia() {

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
                Object[] obj = new Object[7];
                obj[0] = res.getString("nomor_plat");
                obj[1] = res.getString("merk");
                obj[2] = res.getDate("tahun_pembuatan");
                obj[3] = res.getString("warna");
                obj[4] = res.getInt("stok");
                obj[5] = res.getString("harga_sewa_perhari");
                obj[6] = res.getInt("id");

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

    public void clearListPelanggan() {
        model = new DefaultTableModel();
        JTable tblPelanggan;

        tblPelanggan = Transaksi.jTablePelanggan;

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
    void getDataPelanggan() {
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

    public void clearListTransaksi() {
        model = new DefaultTableModel();
        JTable tblPelanggan;

        tblPelanggan = TransaksiList.jTblTransaksi;

        tblPelanggan.setModel(model);
        model.addColumn("Nama Penginput");
        model.addColumn("Role Penginput");
        model.addColumn("Nama Penyewa");
        model.addColumn("No Telepon");
        model.addColumn("Alamat");
        model.addColumn("Merk Mobil");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Status");
        model.addColumn("id Mobil");
        model.addColumn("id transaksi");

        TableColumn tc1 = tblPelanggan.getColumnModel().getColumn(model.getColumnCount() - 2);
        TableColumn tc2 = tblPelanggan.getColumnModel().getColumn(model.getColumnCount() - 1);

        tc1.setMinWidth(0);
        tc1.setMaxWidth(0);
        tc1.setPreferredWidth(0);

        tc2.setMinWidth(0);
        tc2.setMaxWidth(0);
        tc2.setPreferredWidth(0);

    }

//    unutk menampilkan list seluruh mobil yang tersedia ,di panggil  di listmobil
    void getDataTransaksi(Date tanggalFilter) {
        //menghapus isi table tblGaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        String sql = null;
        ResultSet res;
        try {
            //membuat statemen pemanggilan data pada table tblGaji dari database

            conn = getKoneksi();
            stat = conn.createStatement();
            if (tanggalFilter != null) {
                sql = " select t.id_transaksi, t.id_mobil ,u.username as Username,u.role as Role ,p.nama as Nama_Pelanggan ,p.no_telepon as No_Telepon,p.alamat as alamat,m.merk as Merk,t.status, t.tgl_kembali as Tanggal_Kembali  from transaksi t join users u on u.id_users = t.id_user join pelanggan p on p.id_pelanggan = t.id_pelanggan join mobil m on m.id = t.id_mobil where status = 'Belum di Kembalikan' AND t.tgl_kembali = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                // Atur parameter untuk statement
                stmt.setDate(1, tanggalFilter);

                res = stmt.executeQuery();
            } else {
                sql = " select t.id_transaksi, t.id_mobil ,u.username as Username,u.role as Role ,p.nama as Nama_Pelanggan ,p.no_telepon as No_Telepon,p.alamat as alamat,m.merk as Merk,t.status, t.tgl_kembali as Tanggal_Kembali  from transaksi t join users u on u.id_users = t.id_user join pelanggan p on p.id_pelanggan = t.id_pelanggan join mobil m on m.id = t.id_mobil where status = 'Belum di Kembalikan'";
                res = stat.executeQuery(sql);
            }

            //penelusuran baris pada tabel tblGaji dari database
            while (res.next()) {
                Object[] obj = new Object[10];
                obj[0] = res.getString("Username");
                obj[1] = res.getString("role");
                obj[2] = res.getString("nama_pelanggan");
                obj[3] = res.getString("no_telepon");
                obj[4] = res.getString("alamat");
                obj[5] = res.getString("merk");
                obj[6] = res.getString("Tanggal_Kembali");
                obj[7] = res.getString("status");

                obj[8] = res.getInt("id_mobil");
                obj[9] = res.getInt("id_transaksi");
                model.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            err.printStackTrace();
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

//    menambah data mobil
    void insertData(int idMobil, int idPelanggan, Date tanggalSewa, Date tanggalKembali, int hargaSewa, int idUser) throws SQLException {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();
            // Buat statement
            String sql = "INSERT INTO transaksi (id_mobil, id_pelanggan, tgl_sewa, tgl_kembali, harga_sewa, id_user) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
// Atur parameter untuk statement
            stmt.setInt(1, idMobil);
            stmt.setInt(2, idPelanggan);
            stmt.setDate(3, tanggalSewa);
            stmt.setDate(4, tanggalKembali);
            stmt.setInt(5, hargaSewa);
            stmt.setInt(6, idUser);
            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                int currentStock = 0;

                // Ambil jumlah stok saat ini
                String sqlSelect = "SELECT stok FROM mobil WHERE id = ?";
                PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
                stmtSelect.setInt(1, idMobil);
                ResultSet rs = stmtSelect.executeQuery();
                if (rs.next()) {
                    currentStock = rs.getInt("stok");
                }

                // Periksa apakah stok cukup
                if (currentStock > 0) {
                    // Update jumlah stok mobil
                    String sqlUpdate = "UPDATE mobil SET stok = stok - 1 WHERE id = ?";
                    PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
                    stmtUpdate.setInt(1, idMobil);
                    stmtUpdate.executeUpdate();
                } else {
                    // Tampilkan pesan error atau lakukan tindakan lain
                    System.out.println("Stok mobil tidak cukup.");
                }
                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listMobilTersedia();
            } else {
                // Tampilkan pesan gagal
                JOptionPane.showMessageDialog(null, "Data gagal disimpan ke database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Tampilkan pesan error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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

    void editData(String status, int idTransaksi, int idMobil) {
        try {
            conn = getKoneksi();
            stat = conn.createStatement();

            // Buat statement
            String sql = "UPDATE transaksi set status = ?  where id_transaksi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Atur parameter untuk statement
            stmt.setString(1, status);
            stmt.setInt(2, idTransaksi);

            // Eksekusi statement
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {

//                untuk menambha stok mobil yang sudah di kembalikan
                String sqlUpdate = "UPDATE mobil SET stok = stok + 1 WHERE id = ?";
                PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
                stmtUpdate.setInt(1, idMobil);
                stmtUpdate.executeUpdate();

                // Tampilkan pesan berhasil
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                listTransaksi();
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

//    menampilan list mobil yang tersedia
    void listMobilTersedia() {
        clearList();
        getMobilTersedia();
    }

    void listPelanggan() {
        clearListPelanggan();
        getDataPelanggan();
    }

    void listTransaksi() {
        clearListTransaksi();
        Date tanggal = null;
        boolean filter = false;
        getDataTransaksi(tanggal);
    }

    void listTransaksiFilter(Date filterTanggal) {
        clearListTransaksi();
        getDataTransaksi(filterTanggal);
    }

//    method search untuk mencari di tabel mobil
    void search(String query) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(Transaksi.tblMobilTersedia.getModel());
        Transaksi.tblMobilTersedia.setRowSorter(sorter);

        // Buat filter berdasarkan teks yang dimasukkan
        String text = query;
        RowFilter<TableModel, Object> filter = RowFilter.regexFilter("(?i)" + text, 1);

        // Set filter ke tabel
        sorter.setRowFilter(filter);
    }

    void searchPelanggan(String query) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(Transaksi.jTablePelanggan.getModel());
        Transaksi.jTablePelanggan.setRowSorter(sorter);

        // Buat filter berdasarkan teks yang dimasukkan
        String text = query;
        RowFilter<TableModel, Object> filter = RowFilter.regexFilter("(?i)" + text, 0);

        // Set filter ke tabel
        sorter.setRowFilter(filter);
    }

}
