/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public class frmLogin extends javax.swing.JFrame {

    Operaciones oper = null;

    /**
     * Creates new form Login
     */
    public frmLogin() {
        initComponents();
        this.oper = new Operaciones();
        this.setLocationRelativeTo(null);

        this.txtUser.setText("emendoza1");
        this.txtPassword.setText("1234");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUser = new javax.swing.JTextField();
        txtCancelar = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setLocation(new java.awt.Point(300, 400));
        setMaximumSize(new java.awt.Dimension(360, 370));
        setMinimumSize(new java.awt.Dimension(360, 370));
        setResizable(false);
        getContentPane().setLayout(null);

        txtUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUser.setToolTipText("Usuario");
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        getContentPane().add(txtUser);
        txtUser.setBounds(110, 170, 160, 30);

        txtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign-error-icon.png"))); // NOI18N
        txtCancelar.setBorder(null);
        txtCancelar.setBorderPainted(false);
        txtCancelar.setContentAreaFilled(false);
        txtCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign-error-icon.png"))); // NOI18N
        txtCancelar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign-error-icon-2.png"))); // NOI18N
        txtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(txtCancelar);
        txtCancelar.setBounds(220, 270, 50, 50);

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign-check-icon.png"))); // NOI18N
        btnLogin.setBorder(null);
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign-check-icon.png"))); // NOI18N
        btnLogin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign-check-icon-2.png"))); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(110, 270, 50, 50);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword);
        txtPassword.setBounds(110, 230, 160, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/gym-icon.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(130, 20, 130, 120);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Contraseña");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(110, 210, 70, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(110, 150, 45, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/textura.jpg"))); // NOI18N
        jLabel1.setToolTipText("Usuario");
        jLabel1.setMaximumSize(new java.awt.Dimension(500, 200));
        jLabel1.setMinimumSize(new java.awt.Dimension(500, 200));
        jLabel1.setName(""); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 360, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed

    }//GEN-LAST:event_txtUserActionPerformed

    private void txtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCancelarActionPerformed
        this.txtUser.setText("");
        this.txtPassword.setText("");
    }//GEN-LAST:event_txtCancelarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (this.txtUser.getText().equals("") || this.txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un usario y contraseña", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (this.oper.setValidateIngreso(this.txtUser.getText(), this.txtPassword.getText())) {
            this.setVisible(false);
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmLogin().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton txtCancelar;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
