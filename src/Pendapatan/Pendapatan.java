/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendapatan;

import config.Config;
import config.layout;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Pendapatan extends javax.swing.JFrame {

    PendapatanClass pendapatan = new PendapatanClass();

//    method clear (membuat date menjadi null)
    private void clear() {
        jCetak.setEnabled(false);
        jSampaiTanggal.setEnabled(false);
        jDariTanggal.setDate(null);
        jSampaiTanggal.setDate(null);
    }

    public Pendapatan() {
        initComponents();
        pendapatan.listPendapatan();
        layout.Layout(this);
        clear();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPendapatan = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jDariTanggal = new com.toedter.calendar.JDateChooser();
        jSampaiTanggal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCetak = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PENDAPATAN");
        setName("fromPendapatan"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LAPORAN PENDAPATAN");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jTblPendapatan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTblPendapatan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTblPendapatan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 54, 735, 275));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 48, 98), 5, true), "Filter ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(54, 48, 98))); // NOI18N

        jLabel3.setText("Dari Tanggal");

        jDariTanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDariTanggalPropertyChange(evt);
            }
        });

        jSampaiTanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSampaiTanggalPropertyChange(evt);
            }
        });

        jLabel4.setText("Sampai Tanggal");

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCetak.setText("Cetak");
        jCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCetakActionPerformed(evt);
            }
        });

        jButton2.setText("Cetak Semua");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDariTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSampaiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jCetak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDariTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSampaiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jCetak)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 300, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSampaiTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSampaiTanggalPropertyChange
        // TODO add your handling code here:

//        validasi untuk mengecek sampaiTanggal apakah kosong (unutk enabled tombol cetak)
        if (jSampaiTanggal.getDate() == null) {
            jCetak.setEnabled(false);
        } else {
            jCetak.setEnabled(true);
        }
        try {
            if (jDariTanggal.getDate() != null || jSampaiTanggal.getDate() != null) {
                java.util.Date dariTanggal = jDariTanggal.getDate();
                java.sql.Date dariTgl = new java.sql.Date(dariTanggal.getTime());

                java.util.Date sampaiTanggal = jSampaiTanggal.getDate();
                java.sql.Date sampaiTgl = new java.sql.Date(sampaiTanggal.getTime());

                pendapatan.listPendapatanFilter(dariTgl, sampaiTgl);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jSampaiTanggalPropertyChange

    private void jDariTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDariTanggalPropertyChange
        // TODO add your handling code here:
        if (jDariTanggal.getDate() == null) {
            jSampaiTanggal.setEnabled(false);
        } else {
            jSampaiTanggal.setEnabled(true);
        }
    }//GEN-LAST:event_jDariTanggalPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCetakActionPerformed
        // TODO add your handling code here:
        try {
            Config config = new Config();
            File namafile = new File("src/Pendapatan/reportPendapatan.jasper");

            if (jDariTanggal.getDate() == null || jSampaiTanggal.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Pastika Dari Tanggal Dan Sampai Tanggal Sudah Terisi", "Infromation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                java.util.Date dariTanggal = jDariTanggal.getDate();
                java.sql.Date dariTgl = new java.sql.Date(dariTanggal.getTime());

                java.util.Date sampaiTanggal = jSampaiTanggal.getDate();
                java.sql.Date sampaiTgl = new java.sql.Date(sampaiTanggal.getTime());

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("tgl1", dariTgl);
                parameters.put("tgl2", sampaiTgl);

                JasperPrint jp = null;
                try {
                    jp = JasperFillManager.fillReport(namafile.getPath(), parameters, config.getKoneksi());
                } catch (JRException ex) {
                    Logger.getLogger(Pendapatan.class.getName()).log(Level.SEVERE, null, ex);
                }
                JasperViewer.viewReport(jp, false);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jCetakActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Config config = new Config();
            File namafile = new File("src/Pendapatan/reportAllPendapatan.jasper");
            JasperPrint jp = null;
            try {
                jp = JasperFillManager.fillReport(namafile.getPath(), null, config.getKoneksi());
            } catch (JRException ex) {
                Logger.getLogger(Pendapatan.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Pendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pendapatan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jCetak;
    private com.toedter.calendar.JDateChooser jDariTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jSampaiTanggal;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTblPendapatan;
    // End of variables declaration//GEN-END:variables
}
