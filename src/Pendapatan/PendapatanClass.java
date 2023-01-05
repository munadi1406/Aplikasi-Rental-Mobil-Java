/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendapatan;

import static config.Config.getKoneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class PendapatanClass {

    private Statement stat;
    private DefaultTableModel model;
    private static Connection conn;

    public void clearListPendapatan() {
        model = new DefaultTableModel();

        JTable tblMobilTersedia;

        tblMobilTersedia = Pendapatan.jTblPendapatan;

        tblMobilTersedia.setModel(model);
        model.addColumn("Tanggal Sewa");
        model.addColumn("Pedapatan");

    }

//    untuk menampilkan mobil yang tersedia method di panggil di method list mobil tersedia 
    void getListPendapatan(Boolean filter, Date dariTanggal, Date sampaiTanggal) {

        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        String sql;
        try {
            //membuat statemen pemanggilan data pada table tblGaji dari database
            conn = getKoneksi();
            stat = conn.createStatement();
            ResultSet res = null;
            if (!filter) {

                sql = "SELECT tgl_sewa, SUM(harga_sewa) FROM transaksi GROUP BY tgl_sewa";
                res = stat.executeQuery(sql);
            } else {
                sql = "SELECT tgl_sewa, SUM(harga_sewa) FROM transaksi WHERE tgl_sewa BETWEEN ? AND ? GROUP BY tgl_sewa";
                PreparedStatement stmt = conn.prepareStatement(sql);

                // Atur parameter untuk statement
                stmt.setDate(1, dariTanggal);
                stmt.setDate(2, sampaiTanggal);

                res = stmt.executeQuery();
            }

            //penelusuran baris pada tabel tblGaji dari database
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            while (res.next()) {
                Object[] obj = new Object[2];
                obj[0] = res.getString("tgl_sewa");
                double hargaSewa = res.getDouble("sum(harga_sewa)");
                obj[1] = currencyFormat.format(hargaSewa);

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

    void listPendapatan() {
        clearListPendapatan();
        Boolean filter = false;
        Date tgl1 = null;
        Date tgl2 = null;
        getListPendapatan(filter, tgl1, tgl2);
    }

    void listPendapatanFilter(Date dariTanggal, Date sampaiTanggal) {
        clearListPendapatan();
        Boolean filter = true;
        getListPendapatan(filter, dariTanggal, sampaiTanggal);
    }
}
