/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.socialmedia;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ebrar
 */
public class Profile_frm extends javax.swing.JFrame {

    String login_nickname; //I kept the nickname of the logged in user

    //I created the tables model
    DefaultTableModel tbl_SearchTableModel;
    DefaultTableModel tbl_followersTableModel;
    DefaultTableModel tbl_followingTableModel;

    /**
     * Creates new form Profile_frm
     */
    public Profile_frm() {
        initComponents();
        this.setLocationRelativeTo(null); ////making my jframe open in the middle of the screen

        //I updated the models of the tables and prepared appropriate headers
        tbl_SearchTableModel = new DefaultTableModel();
        tbl_SearchTableModel.setColumnIdentifiers(new Object[]{"NICKNAME", "NAME SURNAME"});
        tbl_friendsSearch.setModel(tbl_SearchTableModel);

        tbl_followersTableModel = new DefaultTableModel();
        tbl_followersTableModel.setColumnIdentifiers(new Object[]{"FOLLOWERS"});
        tbl_Followers.setModel(tbl_followersTableModel);

        tbl_followingTableModel = new DefaultTableModel();
        tbl_followingTableModel.setColumnIdentifiers(new Object[]{"FOLLOWING"});
        tbl_Following.setModel(tbl_followingTableModel);

        //When the frame is opened, I called the method so that the data can come
        GetAllData();
    }

    //Check method to not add a user twice
    public boolean ControlTwice(String nickname) {
        boolean value = true; //I created a boolean true to return

        //I created two strings to hold the values from the database
        String tblfollowers;
        String tblfollowing;

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement stmt = conn.createStatement();

            //From the database named follow, I search for login_nickname and nickname with the conjunction and.
            String query = "SELECT * FROM FOLLOW WHERE followers LIKE '%" + login_nickname + "%' AND following LIKE '%" + nickname + "%' ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //I assign data from sql to strings
                tblfollowers = rs.getString("followers");
                tblfollowing = rs.getString("following");

                //I'm comparing values with equals.If both values are the same, I go inside the if structure and return false.
                if (tblfollowers.equalsIgnoreCase(login_nickname) && tblfollowing.equalsIgnoreCase(nickname)) {
                    value = false;
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }

    public void ProfilePicture() { //profile photo setting method
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement stmt = conn.createStatement();

            //I get the file path of the profile photo from the database of that user using the nickname of the logged in user
            String query = "SELECT * FROM USERS WHERE nickname LIKE '%" + login_nickname + "%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String profilepic = rs.getString("profilepic");

                //I change the icon of the label with the file path I got
                ImageIcon picture = new ImageIcon(profilepic);
                lbl_profilepic.setIcon(picture);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //In the loginScreen JFrame, I call this method and insert the nickname of the logged in user.
    public void Welcome(String nickname) {
        login_nickname = nickname;

        //I'm assigning this nickname to the labels I will use in the Profile JFrame.
        lbl_nick.setText(login_nickname);
        lbl_nick1.setText(login_nickname);
    }

    public void FollowersTableRefresh() { //A method that refreshes the table every time there is a change in the table.
        tbl_followersTableModel.setRowCount(0); //I reset the Table for that it doesn't overlap the data every time.
        try {
            //I get the followers of the user with the nickname of the user logged in from the follow database 
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM FOLLOW WHERE Following LIKE '%" + login_nickname + "%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String followers = rs.getString("Followers");

                //After I get the data, I assign them to the relevant table
                tbl_followersTableModel.addRow(new Object[]{followers});
                tbl_Followers.setVisible(true);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FollowingTableRefresh() { //A method that refreshes the table every time there is a change in the table.
        tbl_followingTableModel.setRowCount(0);//I reset the Table for that it doesn't overlap the data every time.
        try {
            //With the nickname of the logged in user from Follow database, I get the user is following
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM FOLLOW WHERE Followers LIKE '%" + login_nickname + "%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String following = rs.getString("Following");

                //After I get the data, I assign them to the relevant table
                tbl_followingTableModel.addRow(new Object[]{following});
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetAllData() {

        //I get data by "Select" from the database to assign nicknames and names to a searchtable
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM USERS";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String nickname = rs.getString("Nickname");
                String nameSurname = rs.getString("Name");

                tbl_SearchTableModel.addRow(new Object[]{nickname, nameSurname});
                tbl_friendsSearch.setVisible(false); //I set the setvisible to false so that the data does not come without searching.
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpane_main = new javax.swing.JTabbedPane();
        pnl_homepage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_nick = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_friendsSearch = new javax.swing.JTable();
        btn_SearchFollow = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pnl_myprofile = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        pnl_profile = new javax.swing.JPanel();
        lbl_profilepic = new javax.swing.JLabel();
        btn_updateProfile = new javax.swing.JButton();
        btn_updatepp = new javax.swing.JButton();
        lbl_nick1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnl_followerstable = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_Followers = new javax.swing.JTable();
        btn_unfollow = new javax.swing.JButton();
        btn_follow1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_Following = new javax.swing.JTable();
        pnl_settings = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnl_background = new javax.swing.JPanel();
        color_background = new javax.swing.JColorChooser();
        cbox_background = new javax.swing.JComboBox<>();
        btn_setBackground = new javax.swing.JButton();
        pnl_settings2 = new javax.swing.JPanel();
        btn_exit = new javax.swing.JButton();
        btn_deleteProfile = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mitem_settings = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mitem_exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(660, 740));
        setMinimumSize(new java.awt.Dimension(660, 740));
        setPreferredSize(new java.awt.Dimension(660, 740));
        setSize(new java.awt.Dimension(10, 20));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        tpane_main.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tpane_main.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        tpane_main.setMaximumSize(new java.awt.Dimension(650, 680));
        tpane_main.setMinimumSize(new java.awt.Dimension(650, 680));
        tpane_main.setPreferredSize(new java.awt.Dimension(650, 680));

        pnl_homepage.setBackground(new java.awt.Color(155, 160, 191));
        pnl_homepage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Home Page", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sylfaen", 1, 36))); // NOI18N
        pnl_homepage.setMinimumSize(new java.awt.Dimension(590, 620));
        pnl_homepage.setPreferredSize(new java.awt.Dimension(590, 620));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel1.setText("Welcome!");

        lbl_nick.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lbl_nick.setText("------------------");

        txt_search.setBackground(new java.awt.Color(204, 204, 204));
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        tbl_friendsSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_friendsSearch.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(tbl_friendsSearch);

        btn_SearchFollow.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_SearchFollow.setText("Follow");
        btn_SearchFollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchFollowActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel2.setText("Search:");

        javax.swing.GroupLayout pnl_homepageLayout = new javax.swing.GroupLayout(pnl_homepage);
        pnl_homepage.setLayout(pnl_homepageLayout);
        pnl_homepageLayout.setHorizontalGroup(
            pnl_homepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_homepageLayout.createSequentialGroup()
                .addGroup(pnl_homepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_homepageLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(pnl_homepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_homepageLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_homepageLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_nick, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnl_homepageLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_homepageLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(btn_SearchFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        pnl_homepageLayout.setVerticalGroup(
            pnl_homepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_homepageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_homepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nick, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnl_homepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_SearchFollow)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        tpane_main.addTab("Home Page", pnl_homepage);

        pnl_myprofile.setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnl_profile.setBackground(new java.awt.Color(208, 174, 174));
        pnl_profile.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Profile", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sylfaen", 1, 26))); // NOI18N

        lbl_profilepic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        btn_updateProfile.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_updateProfile.setText("update my information");
        btn_updateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateProfileActionPerformed(evt);
            }
        });

        btn_updatepp.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_updatepp.setText("update my profile picture");
        btn_updatepp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateppActionPerformed(evt);
            }
        });

        lbl_nick1.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lbl_nick1.setText("--------------------");

        jLabel6.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel6.setText("Nickname:");

        javax.swing.GroupLayout pnl_profileLayout = new javax.swing.GroupLayout(pnl_profile);
        pnl_profile.setLayout(pnl_profileLayout);
        pnl_profileLayout.setHorizontalGroup(
            pnl_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_profileLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbl_profilepic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(pnl_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_updateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_updatepp)
                    .addGroup(pnl_profileLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nick1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
        );
        pnl_profileLayout.setVerticalGroup(
            pnl_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_profileLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_profileLayout.createSequentialGroup()
                        .addGroup(pnl_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lbl_nick1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_updateProfile)
                        .addGap(18, 18, 18)
                        .addComponent(btn_updatepp))
                    .addComponent(lbl_profilepic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        jSplitPane2.setLeftComponent(pnl_profile);

        pnl_followerstable.setBackground(new java.awt.Color(211, 164, 164));
        pnl_followerstable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Followers & Following  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sylfaen", 1, 26))); // NOI18N

        jScrollPane4.setViewportView(tbl_Followers);

        btn_unfollow.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_unfollow.setText("Unfollow");
        btn_unfollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_unfollowActionPerformed(evt);
            }
        });

        btn_follow1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_follow1.setText("Follow");
        btn_follow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_follow1ActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(tbl_Following);

        javax.swing.GroupLayout pnl_followerstableLayout = new javax.swing.GroupLayout(pnl_followerstable);
        pnl_followerstable.setLayout(pnl_followerstableLayout);
        pnl_followerstableLayout.setHorizontalGroup(
            pnl_followerstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_followerstableLayout.createSequentialGroup()
                .addGroup(pnl_followerstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_followerstableLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(pnl_followerstableLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btn_follow1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnl_followerstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_followerstableLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btn_unfollow, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnl_followerstableLayout.setVerticalGroup(
            pnl_followerstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_followerstableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_followerstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(pnl_followerstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_follow1)
                    .addComponent(btn_unfollow))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(pnl_followerstable);

        pnl_myprofile.add(jSplitPane2);

        tpane_main.addTab("My Profile", pnl_myprofile);

        pnl_settings.setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setDividerLocation(500);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnl_background.setBackground(new java.awt.Color(205, 201, 201));
        pnl_background.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Background", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sylfaen", 1, 26))); // NOI18N

        color_background.setBackground(new java.awt.Color(205, 201, 201));

        cbox_background.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cbox_background.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Homepage", "Profile", "Followers & Following", "Background", "Others" }));
        cbox_background.setSelectedIndex(-1);

        btn_setBackground.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_setBackground.setText("Set");
        btn_setBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_setBackgroundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_backgroundLayout = new javax.swing.GroupLayout(pnl_background);
        pnl_background.setLayout(pnl_backgroundLayout);
        pnl_backgroundLayout.setHorizontalGroup(
            pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(color_background, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnl_backgroundLayout.createSequentialGroup()
                        .addComponent(cbox_background, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btn_setBackground)
                        .addGap(0, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_backgroundLayout.setVerticalGroup(
            pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_backgroundLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbox_background, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_setBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(color_background, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jSplitPane1.setTopComponent(pnl_background);

        pnl_settings2.setBackground(new java.awt.Color(176, 146, 146));
        pnl_settings2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Others", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sylfaen", 1, 26))); // NOI18N
        pnl_settings2.setMinimumSize(new java.awt.Dimension(532, 200));
        pnl_settings2.setPreferredSize(new java.awt.Dimension(532, 200));

        btn_exit.setBackground(new java.awt.Color(168, 127, 127));
        btn_exit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_deleteProfile.setBackground(new java.awt.Color(168, 127, 127));
        btn_deleteProfile.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_deleteProfile.setText("Delete My Account");
        btn_deleteProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_settings2Layout = new javax.swing.GroupLayout(pnl_settings2);
        pnl_settings2.setLayout(pnl_settings2Layout);
        pnl_settings2Layout.setHorizontalGroup(
            pnl_settings2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_settings2Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(btn_exit)
                .addGap(47, 47, 47)
                .addComponent(btn_deleteProfile)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        pnl_settings2Layout.setVerticalGroup(
            pnl_settings2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_settings2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnl_settings2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_exit)
                    .addComponent(btn_deleteProfile))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(pnl_settings2);

        pnl_settings.add(jSplitPane1);

        tpane_main.addTab("Settings", pnl_settings);

        getContentPane().add(tpane_main);

        jMenu1.setBorder(null);
        jMenu1.setText("More");
        jMenu1.setFont(new java.awt.Font("Sylfaen", 1, 20)); // NOI18N

        mitem_settings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitem_settings.setText("Settings");
        mitem_settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_settingsActionPerformed(evt);
            }
        });
        jMenu1.add(mitem_settings);
        jMenu1.add(jSeparator1);

        mitem_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitem_exit.setText("Exit");
        mitem_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });
        jMenu1.add(mitem_exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SearchFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchFollowActionPerformed
        if (tbl_friendsSearch.getSelectedRow() > -1) { //I check if data is selected in table
            //I get the value of the selected data in the table and then I throw the relevant data of that value into the String
            int row = tbl_friendsSearch.getSelectedRow();
            int count = tbl_friendsSearch.getSelectedColumn();
            String nickname = (String) tbl_SearchTableModel.getValueAt(row, 0);

            //I call the control method to not add the same data to the table twice
            Boolean control = ControlTwice(nickname);

            if (control == true) { //If the return value from the control method is true, it goes inside the if structure.
                if (!nickname.equals(login_nickname)) { //Since the user cannot follow himself, I am comparing here. If it returns false, it enters the if structure.
                    try {
                        //I'm throwing the relevant data to the follow database with "Insert"
                        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
                        Statement stmt = conn.createStatement();
                        String query = "INSERT INTO SA.FOLLOW VALUES('" + login_nickname + "', '" + nickname + "')";
                        stmt.executeUpdate(query);
                        conn.close();

                        //I'm refreshing the tables
                        FollowersTableRefresh();
                        FollowingTableRefresh();

                    } catch (SQLException ex) {
                        Logger.getLogger(Profile_frm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "You can't add the same person"); //If the user wants to follow the same person, I give a warning with JOptionPane
            }
        }
    }//GEN-LAST:event_btn_SearchFollowActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        String text = txt_search.getText(); //I get the text to be searched 
        tbl_SearchTableModel.setRowCount(0); //I reset the Table for that it doesn't overlap the data every time.

        try {
            //In the Follow database, I search for the text I got with "Search"
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM USERS WHERE Name LIKE '%" + text + "%' OR Nickname LIKE '%" + text + "%' ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //if the search matches I throw the incoming data to Strings
                String nickname = rs.getString("Nickname");
                String nameSurname = rs.getString("Name");

                //inserting the data into the table
                tbl_SearchTableModel.addRow(new Object[]{nickname, nameSurname});
                tbl_friendsSearch.setVisible(true);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_searchKeyReleased

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        //When the exit button is clicked, I print "are you sure" with JOptionpane and put the response in integer
        int value = JOptionPane.showConfirmDialog(this, "Do you want to exit on your profile?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (value == JOptionPane.YES_OPTION) { //if the value is equal to yes it goes inside the if structure
            //I close this JFrame and switch to LoginScreen Jframe because it is logged out.
            this.setVisible(false);
            LoginScreen_frm f = new LoginScreen_frm();
            f.setVisible(true);
        }
    }//GEN-LAST:event_btn_exitActionPerformed

    private void mitem_settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_settingsActionPerformed
        tpane_main.setSelectedIndex(2); //I opened that page in tpane according to the index of the settings page
    }//GEN-LAST:event_mitem_settingsActionPerformed

    private void btn_setBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_setBackgroundActionPerformed
        int index = cbox_background.getSelectedIndex(); //I put the selected index from the combobox to the integer

        //I created a switch-case structure and the index goes into which case equals it.
        switch (index) {
            case 0:
                //I changed the color of the selected page according to the value returned from the color chooser
                pnl_homepage.setBackground(color_background.getColor());
                break;
            case 1:
                pnl_profile.setBackground(color_background.getColor());
                break;
            case 2:
                pnl_followerstable.setBackground(color_background.getColor());
                break;
            case 3:
                pnl_background.setBackground(color_background.getColor());
                break;
            case 4:
                pnl_settings2.setBackground(color_background.getColor());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btn_setBackgroundActionPerformed

    private void btn_updateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateProfileActionPerformed
        /*the nickname of the logged in user is assigned 
        to the UpdateProfile and then the UpdateProfile JFrame is opened.*/
        UpdateProfile_frm frm = new UpdateProfile_frm();
        frm.Information(lbl_nick.getText());
        frm.setVisible(true);
    }//GEN-LAST:event_btn_updateProfileActionPerformed

    private void btn_updateppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateppActionPerformed

        JFileChooser fileChooser = new JFileChooser(); //I am creating a filechooser object.

        //I create filter to filter which file type will be accepted and I set the filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", new String[]{"jpg", "jpeg"});
        fileChooser.setFileFilter(filter);

        //I'm removing the accept all file types filter
        fileChooser.setAcceptAllFileFilterUsed(false);

        //I select file and return an integer value from here
        int value = fileChooser.showOpenDialog(this);

        //The value is 0 when the file arrives, and the value 1 comes when you click cancel or cross.
        if (value == 0) { //if 0 value comes, i put it inside "if" structure

            //I'm creating a "file" object and put the file path of the photo from filechooser
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            //I create an imageicon and put the information in the file into it and I set the icon of the label.
            ImageIcon picture = new ImageIcon(file.toString());
            lbl_profilepic.setIcon(picture);

            try { //I save the file path with "Update" to "Users" database
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
                PreparedStatement ps = conn.prepareStatement("UPDATE USERS SET profilepic = ? WHERE nickname = ? ");
                ps.setString(1, file.toString());
                ps.setString(2, login_nickname);
                ps.executeUpdate();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Profile_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_updateppActionPerformed

    private void btn_deleteProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteProfileActionPerformed
        //I request a yes or no option from the user with JOptionpane, then I assign the return value to integer.
        int value = JOptionPane.showConfirmDialog(this, "Do you want to delete on your profile?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        //If the user says yes, it is entered into the "if" structure and the user's account is deleted with "Delete" from both databases.
        if (value == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
                PreparedStatement ps = conn.prepareStatement("DELETE FROM SA.USERS WHERE nickname = ?");
                ps.setString(1, login_nickname);

                PreparedStatement ps2 = conn.prepareStatement("DELETE FROM SA.FOLLOW WHERE followers = ? OR following = ?");
                ps2.setString(1, login_nickname);
                ps2.setString(2, login_nickname);

                ps2.executeUpdate();
                ps.executeUpdate();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Profile_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
            //After the account is deleted, Profile Jframe is exited and the LoginScreen Jframe is opened.
            this.setVisible(false);
            LoginScreen_frm f = new LoginScreen_frm();
            f.setVisible(true);
        }
    }//GEN-LAST:event_btn_deleteProfileActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //I request a yes or no option from the user with JOptionpane, then I assign the return value to integer.
        int value = JOptionPane.showConfirmDialog(this, "Do you want to exit on your profile?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        //If the user says yes, it is entered into the "if" structure and Profile Jframe is exited and the LoginScreen Jframe is opened.
        if (value == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            LoginScreen_frm f = new LoginScreen_frm();
            f.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btn_unfollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_unfollowActionPerformed
        if (tbl_Following.getSelectedRow() > -1) { //I check if data is selected in table

            //I get the value of the selected data in the table and then I throw the relevant data of that value into the String
            int row = tbl_Following.getSelectedRow();
            String followers = (String) tbl_followingTableModel.getValueAt(row, 0);

            try {
                //I delete the user that the user wants to unfollow from the follow database with "Delete"
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
                PreparedStatement ps = conn.prepareStatement("DELETE FROM SA.FOLLOW WHERE followers = ? AND following = ? ");
                ps.setString(1, login_nickname);
                ps.setString(2, followers);
                ps.executeUpdate();
                conn.close();

                //I'm refreshing the tables
                FollowersTableRefresh();
                FollowingTableRefresh();

            } catch (SQLException ex) {
                Logger.getLogger(Profile_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_unfollowActionPerformed

    private void btn_follow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_follow1ActionPerformed
        if (tbl_Followers.getSelectedRow() > -1) { //I check if data is selected in table

            //I get the value of the selected data in the table and then I throw the relevant data of that value into the String
            int row = tbl_Followers.getSelectedRow();
            String nickname = (String) tbl_followersTableModel.getValueAt(row, 0);

            //I call the control method to not add the same data to the table twice
            Boolean control = ControlTwice(nickname);

            if (control == true) { //If the return value from the control method is true, it goes inside the if structure.
                if (!nickname.equals(login_nickname)) { //Since the user cannot follow himself, I am comparing here. If it returns false, it enters the if structure.
                    try {
                        //I'm throwing the relevant data to the follow database with "Insert"
                        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "SA", "as");
                        Statement stmt = conn.createStatement();
                        String query = "INSERT INTO SA.FOLLOW VALUES('" + login_nickname + "', '" + nickname + "')";
                        stmt.executeUpdate(query);
                        conn.close();

                        //I'm refreshing the tables
                        FollowersTableRefresh();
                        FollowingTableRefresh();

                    } catch (SQLException ex) {
                        Logger.getLogger(Profile_frm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "You can't add the same person"); //If the user wants to follow the same person, I give a warning with JOptionPane
            }
        }
    }//GEN-LAST:event_btn_follow1ActionPerformed

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
            java.util.logging.Logger.getLogger(Profile_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile_frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SearchFollow;
    private javax.swing.JButton btn_deleteProfile;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_follow1;
    private javax.swing.JButton btn_setBackground;
    private javax.swing.JButton btn_unfollow;
    private javax.swing.JButton btn_updateProfile;
    private javax.swing.JButton btn_updatepp;
    private javax.swing.JComboBox<String> cbox_background;
    private javax.swing.JColorChooser color_background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_nick1;
    private javax.swing.JLabel lbl_profilepic;
    private javax.swing.JMenuItem mitem_exit;
    private javax.swing.JMenuItem mitem_settings;
    private javax.swing.JPanel pnl_background;
    private javax.swing.JPanel pnl_followerstable;
    private javax.swing.JPanel pnl_homepage;
    private javax.swing.JPanel pnl_myprofile;
    private javax.swing.JPanel pnl_profile;
    private javax.swing.JPanel pnl_settings;
    private javax.swing.JPanel pnl_settings2;
    private javax.swing.JTable tbl_Followers;
    private javax.swing.JTable tbl_Following;
    private javax.swing.JTable tbl_friendsSearch;
    private javax.swing.JTabbedPane tpane_main;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
