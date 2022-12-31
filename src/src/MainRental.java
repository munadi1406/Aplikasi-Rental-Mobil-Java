/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import Mobil.Mobil;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class MainRental {
//    private String Password="";
//    private String Username = "root";
//    private String Database = "rental_mobil";
    private static Connection conn;
    private Statement stat;
    private ResultSet res;
   
    
    
     public static Connection getKoneksi( ){
               String host       = "jdbc:mysql://localhost/rental_mobil",
                          user       = "root",
                          pass       = "";
               try{
                      conn = (Connection) DriverManager.getConnection(host, user, pass);
                      System.out.println("Koneksi Berhasil");
               }catch (SQLException err){
                       JOptionPane.showMessageDialog(null,"Maaf Terjadi Kesalahan Pada Bagian: \n["+err.toString()+"]");
               }
               return conn;
      }

    private DefaultTableModel model;
    
    /**
     *
     */
     
      
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
           Statement stat = (Statement) getKoneksi().createStatement( );
           String sql        = "Select * from mobil";
           ResultSet res   = stat.executeQuery(sql);

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
      }
}

    
   
     
    

}
