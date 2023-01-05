/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobil;

import config.layout;
import config.Config;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Asus
 */
public class MobilAllList extends javax.swing.JFrame {

    /**
     * Creates new form MobilAllList
     */
    MobilClass mobil = new MobilClass();
    private final String val = "All";
    private int id;

    //method clear data
    public void clear() {
        TNomorPlat.setText(null);
        TMerk.setText(null);
        DDate.setDate(null);
        TWarna.setText(null);
        TStok.setText(null);
        THargaSewaPerH.setText(null);
        jSimpan.setEnabled(true);
        jEdit.setEnabled(false);
        jHapus.setEnabled(false);

    }

    public MobilAllList() {
        initComponents();
        layout.Layout(this);
        mobil.listMobil();
        jEdit.setEnabled(false);
        jHapus.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMobil = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TNomorPlat = new javax.swing.JTextField();
        TMerk = new javax.swing.JTextField();
        THargaSewaPerH = new javax.swing.JTextField();
        TStok = new javax.swing.JTextField();
        DDate = new com.toedter.calendar.JDateChooser();
        TWarna = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jJumlahMobil = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSimpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMobil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMobilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMobil);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 140));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("MOBIL LIST");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 190, -1));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Nomor Plat");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel7.setText("Merk");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 60, 30));

        jLabel3.setText("Tahun Pembuatan");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 140, 30));

        jLabel5.setText("Warna");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 60, 30));

        jLabel8.setText("Stok");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 50, 30));

        jLabel6.setText("Harga Sewa Perhari");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 170, -1));
        jPanel1.add(TNomorPlat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 420, -1));
        jPanel1.add(TMerk, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 420, -1));

        THargaSewaPerH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                THargaSewaPerHKeyTyped(evt);
            }
        });
        jPanel1.add(THargaSewaPerH, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 420, -1));

        TStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TStokKeyTyped(evt);
            }
        });
        jPanel1.add(TStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 420, -1));
        jPanel1.add(DDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 420, -1));
        jPanel1.add(TWarna, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 420, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 530, 290));

        jLabel9.setText("Cari Berdasarkan Merk");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 130, 20));

        jLabel10.setText("JUMLAH MOBIL : ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));

        jJumlahMobil.setText(".....");
        getContentPane().add(jJumlahMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jSimpan.setText("Simpan");
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jEdit.setText("Edit");
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jHapus.setText("Hapus");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jHapus))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(47, 47, 47)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSimpan)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEdit)
                    .addComponent(jHapus))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 200, 140));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Landingpage (1).jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 800, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        // TODO add your handling code here:
        String nomorPlat = TNomorPlat.getText();
        String merk = TMerk.getText();

        Date tanggal = DDate.getDate();
        java.sql.Date tanggalSql = null;
        if (tanggal != null) {
            long time = tanggal.getTime();
            tanggalSql = new java.sql.Date(time);
        }

        String warna = TWarna.getText();
        String hSewa = THargaSewaPerH.getText();
        String stok = TStok.getText();
        int stokInt = 0;

        if (nomorPlat == null || nomorPlat.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nomor Plat Belum Di isi");
        } else if (merk == null || merk.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Merk Belum Di isi");
        } else if (tanggal == null) {
            JOptionPane.showMessageDialog(null, "Tanggal Belum Di Piih");
        } else if (warna == null || warna.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Warna Belum Di isi");
        } else if (stok == null || stok.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Stok Belum Di isi");
        } else if (hSewa == null || hSewa.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Harga Sewa Belum Di isi");
        } else {
            try {
                stokInt = Integer.parseInt(stok);
                mobil.insertData(nomorPlat, merk, tanggalSql, warna, stokInt, hSewa);
                clear();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Stok Harus Berupa Angka");
            }
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TStokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TStokKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Masukkan Hanya Angka ");
            evt.consume();
        }
    }//GEN-LAST:event_TStokKeyTyped

    private void THargaSewaPerHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THargaSewaPerHKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Masukkan Hanya Angka ");
            evt.consume();
        }
    }//GEN-LAST:event_THargaSewaPerHKeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        String text = jTextField6.getText();
        mobil.search(text);
    }//GEN-LAST:event_jTextField6KeyTyped

    private void tblMobilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMobilMouseClicked
        // TODO add your handling code here:
        jSimpan.setEnabled(false);
        jEdit.setEnabled(true);
        jHapus.setEnabled(true);
        try {
            int row = tblMobil.rowAtPoint(evt.getPoint());
            int col = tblMobil.columnAtPoint(evt.getPoint());
            if (row >= 0 && col >= 0) {
                String noPlat = (String) tblMobil.getValueAt(row, 0);
                String merk = (String) tblMobil.getValueAt(row, 1);
                try {
                    java.sql.Date tanggal = (java.sql.Date) tblMobil.getValueAt(row, 2);
                    String tahunP = tanggal.toString();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(tahunP);
                    DDate.setDate(date);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi Error Saat Mengambil Data Tanggal", "error", JOptionPane.ERROR_MESSAGE);
                }
                String warna = (String) tblMobil.getValueAt(row, 3);
                int stok = (int) tblMobil.getValueAt(row, 4);
                String hargaPerH = (String) tblMobil.getValueAt(row, 5);
                id = (int) tblMobil.getModel().getValueAt(row, 6);

//            menampilkan ke textField
                TNomorPlat.setText(noPlat);
                TMerk.setText(merk);
                TWarna.setText(warna);
                TStok.setText(String.valueOf(stok));
                THargaSewaPerH.setText(hargaPerH);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Error Saat Mengambil Data", "error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tblMobilMouseClicked

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        // TODO add your handling code here:
//        menangkap id berdasarkan baris yang di ambil (id berasal dari variabel global di atas)
        int getId = id;
        String nomorPlat = TNomorPlat.getText();
        String merk = TMerk.getText();

        Date tanggal = DDate.getDate();
        long time = tanggal.getTime();
        java.sql.Date tanggalSql = new java.sql.Date(time);

        String warna = TWarna.getText();
        String HSewa = THargaSewaPerH.getText();
        String stok = TStok.getText();
        int stokInt = Integer.parseInt(stok);

        try {
            mobil.editData(nomorPlat, merk, tanggalSql, warna, stokInt, HSewa, getId);
            clear();
        } catch (Exception e) {
            
        }

    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        // TODO add your handling code here:
        int getId = id;
        try {
            mobil.hapusData(getId);
            clear();
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_jHapusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Config config = new Config();
        File namafile = new File("src/Mobil/MobilReport.jasper");
        JasperPrint jp = null;
        try {
            jp = JasperFillManager.fillReport(namafile.getPath(), null, config.getKoneksi());
        } catch (JRException ex) {
            Logger.getLogger(MobilAllList.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer.viewReport(jp, false);
        try {
            JasperExportManager.exportReportToPdfFile(jp, "output.pdf");
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MobilAllList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MobilAllList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MobilAllList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MobilAllList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MobilAllList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DDate;
    private javax.swing.JTextField THargaSewaPerH;
    private javax.swing.JTextField TMerk;
    private javax.swing.JTextField TNomorPlat;
    private javax.swing.JTextField TStok;
    private javax.swing.JTextField TWarna;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jHapus;
    static javax.swing.JLabel jJumlahMobil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSimpan;
    private javax.swing.JTextField jTextField6;
    public static javax.swing.JTable tblMobil;
    // End of variables declaration//GEN-END:variables
}
