/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static config.Config.getKoneksi;
import java.sql.Connection;

/**
 *
 * @author Asus
 */

public class MobilClass {
    
    private Statement stat;
    private DefaultTableModel model;
    private static Connection conn;
    
    public void clearList(){
       model = new DefaultTableModel ();
       
        JTable tblMobil = Mobil.tblMobil;

             tblMobil.setModel(model);
             model.addColumn("Nomot Plat");
             model.addColumn("Merk");
             model.addColumn("tahun pembuatan");
             model.addColumn("warna");
             model.addColumn("stok");
             model.addColumn("Harga Sewa");
            
             getData();
    }
    
    
    private void getData( ){
         
     //menghapus isi table tblGaji
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
        try{
              //membuat statemen pemanggilan data pada table tblGaji dari database
               conn = getKoneksi();
               stat = conn.createStatement();
               String sql = "Select * from mobil";
               ResultSet res = stat.executeQuery(sql);

              //penelusuran baris pada tabel tblGaji dari database
              while(res.next ()){
                   Object[ ] obj = new Object[6];
                   obj[0] = res.getString("nomor_plat");
                   obj[1] = res.getString("merk");
                   obj[2] = res.getDate("tahun_pembuatan");
                   obj[3] = res.getString("warna");
                   obj[4] = res.getInt("stok");
                   obj[5] = res.getString("harga_sewa_perhari");

                   model.addRow(obj);
               }
         }catch(SQLException err){
               JOptionPane.showMessageDialog(null, err.getMessage() );
         }finally {
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
}

