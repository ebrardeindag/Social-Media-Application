/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.socialmedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ebrar
 */
public class LoginScreen_frm extends javax.swing.JFrame {

    //I generate objects from those jframes to be able to pass to side jframes or access the methods of the side class.
    NewAccount_frm register = new NewAccount_frm();
    Profile_frm profile = new Profile_frm();

    //I created two arraylists to put the username and password of the users who have created an account.
    ArrayList<String> nicknameList = new ArrayList<>();
    ArrayList<String> passwordList = new ArrayList<>();

    /**
     * Creates new form main
     */
    public LoginScreen_frm() {
        initComponents();

        this.setLocationRelativeTo(null); //making my jframe open in the middle of the screen
        toList(); //I call this method for arraylists
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_nickname = new javax.swing.JTextField();
        txtp_password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_login = new javax.swing.JButton();
        btn_createAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(145, 182, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LOGIN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Sylfaen", 1, 48))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 350));
        jPanel1.setPreferredSize(new java.awt.Dimension(510, 350));

        txt_nickname.setBackground(new java.awt.Color(204, 204, 204));

        txtp_password.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nickname:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Password:");

        btn_login.setBackground(new java.awt.Color(204, 204, 204));
        btn_login.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        btn_createAccount.setBackground(new java.awt.Color(204, 204, 204));
        btn_createAccount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_createAccount.setText("Create a New Account");
        btn_createAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createAccountMouseClicked(evt);
            }
        });
        btn_createAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(txt_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_createAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(btn_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtp_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))))
                .addGap(92, 92, 92))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtp_password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addComponent(btn_login)
                .addGap(18, 18, 18)
                .addComponent(btn_createAccount)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_createAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createAccountMouseClicked
        //When I click this button, I switch to the jframe named newAccount to create a new account and close this jframe.
        register.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_createAccountMouseClicked

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        //When I click this button, I first look for the nickname entered in the txt_nickname in the nickname-arraylist.
        //If nickname is found in nickname-arraylist i check password-arraylist for same index of arraylist for password.
        for (int i = 0; i < nicknameList.size(); i++) {
            if (txt_nickname.getText().equalsIgnoreCase(nicknameList.get(i))) {
                if (txtp_password.getText().equalsIgnoreCase(passwordList.get(i))) {
                    
                    /*If both are correct, I switch to profile-jframe and pass the user's 
                    information to profile-jframe with the help of methods while passing.*/
                    
                    profile.Welcome(txt_nickname.getText());
                    profile.ProfilePicture();
                    profile.FollowingTableRefresh();
                    profile.FollowersTableRefresh();
                    
                    //I create jOptionpane that it is true
                    JOptionPane.showMessageDialog(rootPane, "Login Successful");
                    
                    //In order to enter the user's profile, I switch to the Profile-Jframe I created and close the Login-JFrame.
                    profile.setVisible(true);
                    this.setVisible(false);
                    break;

                } else {
                    //I am giving error with JOptionpane when Password is wrong.
                    JOptionPane.showMessageDialog(rootPane, "Password is wrong");
                    break;
                }
            }
            if (i == nicknameList.size()-1) {
                //I am giving error with JOptionpane when user not found.
                JOptionPane.showMessageDialog(rootPane, "User not found");
            }
        }
    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_createAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createAccountActionPerformed
        register.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_createAccountActionPerformed
    
    
    public void toList() {
        //I get the nickname and password information from the database and put them in the appropriate arraylists.
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                nicknameList.add(rs.getString("Nickname"));
                passwordList.add(rs.getString("Password"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen_frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_createAccount;
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_nickname;
    private javax.swing.JPasswordField txtp_password;
    // End of variables declaration//GEN-END:variables
}
