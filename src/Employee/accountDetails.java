/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import Startups.Login;
import admin.*;
import java.awt.Color;
import Startups.StartupPanel;
import config.Session;
import config.dbConnect;
import config.passwordHasher;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;



import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author DANIEL FAILADONA
 */
public class accountDetails extends javax.swing.JFrame {

    private Color H;
    Color h = new Color(51,51,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public accountDetails() {
        initComponents();
    }
    
    
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;

    public int FileExistenceChecker(String path) {
        File file = new File(path);
        String fileName = file.getName();

        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);

        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);

            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }

        return -1;
    }

    public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void imageUpdater(String existingFilePath, String newFilePath) {
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: " + e);
            }
        } else {
            try {
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error on update!");
            }
        }
    }
    
    
    
    
//================================================================================
    
    public static String Phone, usname;
    
    
    
//    public boolean updateCheck() {
//        
//        dbConnect dbc = new dbConnect();
//        Session sess = Session.getInstance();
//        int u = sess.getUid();
//        String p = phone.getText().trim();
//        String us = MR_username.getText().trim();
//        dbConnect connector = new dbConnect();
//
//        
//        
//        try {
//            String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
//            PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);
//
//            ResultSet resultSet = pstmt.executeQuery();
//
//            if (resultSet.next()) {
//                uCheck = resultSet.getInt("u_id");   // Update the outer `userId` correctly
//            }
//        } catch (SQLException ex) {
//            System.out.println("SQL Exception: " + ex);
//        }
//        
//        
//        
//
//        try {
//            String query = "SELECT * FROM tbl_accounts WHERE (u_username='" + us + "'OR u_phone='" + p + "') AND u_id != '" + u + "'";
//
//            System.out.println("u_username: " + us);
//            System.out.println("u_phone: " + p);
//            System.out.println("u_id: " + sess.getUid());
//
//            ResultSet resultSet = dbc.getData(query);
//            if (resultSet.next()) {
//                Phone = resultSet.getString("u_phone");
//                if (Phone.equals(p) && ) {
//                    JOptionPane.showMessageDialog(null, "Phone Number is Already Used");
//                    phone.setText("");
//                }
//
//                usname = resultSet.getString("u_username");
//                if (usname.equals(us)) {
//                    JOptionPane.showMessageDialog(null, "Username is Already Used");
//                    MR_username.setText("");
//                }
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException ex) {
//            System.out.println("" + ex);
//            return false;
//        }
//    }
    
    
    
    
    
    
    
    
    
    public boolean updateCheck() {
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        String p = phone.getText().trim();
        String us = MR_username.getText().trim();
        int currentUID = sess.getUid();

        System.out.println("[1] Trimmed input - Username: " + us);
        System.out.println("[2] Trimmed input - Phone: " + p);
        System.out.println("[3] Current Session UID: " + currentUID);

        try {
            String query = "SELECT u_id, u_username, u_phone FROM tbl_accounts WHERE (u_username = '" + us + "' OR u_phone = '" + p + "')";
            System.out.println("[4] Executing query: " + query);

            ResultSet resultSet = dbc.getData(query);
            boolean isDuplicate = false;

            while (resultSet.next()) {
                int uid = resultSet.getInt("u_id");
                String dbPhone = resultSet.getString("u_phone");
                String dbUsername = resultSet.getString("u_username");

                System.out.println("[5] Found row:");
                System.out.println("     - UID: " + uid);
                System.out.println("     - DB Phone: " + dbPhone);
                System.out.println("     - DB Username: " + dbUsername);

                if (uid != currentUID) {
                    System.out.println("[6] UID mismatch — checking for duplicates...");

                    if (dbPhone.equals(p)) {
                        System.out.println("[7] Duplicate phone detected (UID: " + uid + ")");
                        JOptionPane.showMessageDialog(null, "Phone Number is Already Used");
                        phone.setText("");
                        isDuplicate = true;
                    }

                    if (dbUsername.equals(us)) {
                        System.out.println("[8] Duplicate username detected (UID: " + uid + ")");
                        JOptionPane.showMessageDialog(null, "Username is Already Used");
                        MR_username.setText("");
                        isDuplicate = true;
                    }
                } else {
                    System.out.println("[9] Match is from current user. Skipping.");
                }
            }

            System.out.println("[10] Final isDuplicate: " + isDuplicate);
            return isDuplicate;

        } catch (SQLException ex) {
            System.out.println("[ERROR] SQLException: " + ex.getMessage());
            return true; // Assume duplicate to be safe
        }
    }



   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public boolean updateCheck() {
//        dbConnect dbc = new dbConnect();
//        Session sess = Session.getInstance();
//        int u = sess.getUid();
//        String p = phone.getText().trim();
//        String us = MR_username.getText().trim();
//
//        try {
//            String query = "SELECT * FROM tbl_accounts WHERE (u_username='" + us + "'OR u_phone='" + p + "') AND u_id != '" + u + "'";
//
//            System.out.println("u_username: " + us);
//            System.out.println("u_phone: " + p);
//            System.out.println("u_id: " + sess.getUid());
//
//            ResultSet resultSet = dbc.getData(query);
//            if (resultSet.next()) {
//                Phone = resultSet.getString("u_phone");
//                if (Phone.equals(p)) {
//                    JOptionPane.showMessageDialog(null, "Phone Number is Already Used");
//                    phone.setText("");
//                }
//
//                usname = resultSet.getString("u_username");
//                if (usname.equals(us)) {
//                    JOptionPane.showMessageDialog(null, "Username is Already Used");
//                    MR_username.setText("");
//                }
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException ex) {
//            System.out.println("" + ex);
//            return false;
//        }
//    }
    
    
    
    
    
//================================================================================

    public void logEvent(int userId, String username, String action) 
    {
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try 
        {
            String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);

            /*            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setString(4, userType);*/
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                if (con != null) 
                {
                    con.close();
                }
            } catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }    
//================================================================================

    


    

    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MR_username = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Lname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Fname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        confirm = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        Remove = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        Select = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Main.setBackground(new java.awt.Color(121, 75, 61));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(181, 126, 110));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(255, 255, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Account Details");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1310, 40));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 100));

        MR_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MR_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MR_usernameActionPerformed(evt);
            }
        });
        Main.add(MR_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 560, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Last Name:");
        Main.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 110, 40));

        Lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LnameActionPerformed(evt);
            }
        });
        Main.add(Lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 560, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("  First Name:");
        Main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 120, 40));

        Fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FnameActionPerformed(evt);
            }
        });
        Main.add(Fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 560, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Username:");
        Main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 110, 40));

        confirm.setBackground(new java.awt.Color(255, 255, 255));
        confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmMouseExited(evt);
            }
        });
        confirm.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Confirm");
        confirm.add(jLabel11);
        jLabel11.setBounds(0, 10, 110, 20);

        Main.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 520, 110, 40));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Phone");
        Main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 110, 40));

        phone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        Main.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, 560, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        image.setBackground(new java.awt.Color(181, 126, 110));
        jPanel1.add(image);
        image.setBounds(10, 10, 190, 170);

        Main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, 210, 190));

        Remove.setBackground(new java.awt.Color(255, 255, 255));
        Remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RemoveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RemoveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RemoveMouseExited(evt);
            }
        });
        Remove.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Remove");
        Remove.add(jLabel21);
        jLabel21.setBounds(0, 10, 110, 20);

        Main.add(Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 410, 110, 40));

        Select.setBackground(new java.awt.Color(255, 255, 255));
        Select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SelectMouseExited(evt);
            }
        });
        Select.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Select");
        Select.add(jLabel22);
        jLabel22.setBounds(0, 10, 110, 20);

        Main.add(Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 410, 110, 40));

        logout.setBackground(new java.awt.Color(255, 255, 255));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Back");
        logout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 130, -1));

        Main.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        EmployeeDashboard ed = new EmployeeDashboard();
        ed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void MR_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MR_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MR_usernameActionPerformed

    private void LnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LnameActionPerformed

    private void FnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FnameActionPerformed

    private void confirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseClicked
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        String fn = Fname.getText().trim();
        String ln = Lname.getText().trim();
        String p = phone.getText().trim();
        String uname = MR_username.getText().trim();
        dbConnect connector = new dbConnect();
        int userId = 0;
        String uname2 = null;
        String sq = "";
        String sa = "";

        if(uname.isEmpty() || ln.isEmpty() || fn.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Fill All Boxes");
        }else if(updateCheck())
        {
            System.out.println("Duplicate Exists");
        }else
        {
            try {
                    String query = "SELECT * FROM tbl_accounts WHERE u_id='" + sess.getUid() + "'";
                    ResultSet rs = dbc.getData(query);
                    if (rs.next()) 
                    {
                        String npass = rs.getString("u_password");
                        String at = rs.getString("u_type");
                        String s = rs.getString("u_status");

                        dbc.updateData("UPDATE tbl_accounts SET u_fname = '" + fn + "', u_lname = '" + ln + "', u_username = '" + uname + "',"
                                + " u_password = '" + npass + "', u_phone = '" + p + "', u_type = '" + at + "', u_status = '" + s + "', u_image = '" + destination + "', security_question= '" + sq + "', security_answer= '" + sa + "' WHERE u_id = '" + sess.getUid() + "'");
                        
                        
//                        if (dbc.insertData("INSERT INTO tbl_accounts (u_fname, u_lname, u_username, u_type, u_password, u_phone, u_status, u_image, security_question, security_answer) "
//                                + "VALUES ('" + fn + "', '" + ln + "', '" + uname + "', '" + at + "','" + npass + "', '" + p + "',  '" + s + "',  '" + destination + "',  '" + sq + "',  '" + sa + "')"))
                        

                        try 
                        {
                            String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
                            PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

                            ResultSet resultSet = pstmt.executeQuery();

                            if (resultSet.next()) {
                                userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                                uname2 = resultSet.getString("u_username");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQL Exception: " + ex);
                        }

                        logEvent(userId, uname2, "User Changed Their Details");

                        EmployeeDashboard ed = new EmployeeDashboard();
                        ed.setVisible(true);
                        this.dispose();

//                        }else
//                        {
//                            JOptionPane.showMessageDialog(null, "Unknown Error Occured");
//                            EmployeeDashboard ed = new EmployeeDashboard();
//                            ed.setVisible(true);
//                            this.dispose();
//                        }
                    }
                }catch (SQLException ex) {
                System.out.println("" + ex);
            }
        }
        
    }//GEN-LAST:event_confirmMouseClicked

    private void confirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseEntered
        confirm.setBackground(h);
    }//GEN-LAST:event_confirmMouseEntered

    private void confirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseExited
        confirm.setBackground(d);
    }//GEN-LAST:event_confirmMouseExited

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        /*Session sess = Session.getInstance();
        if(sess.getUid() == 0)
        {
        JOptionPane.showMessageDialog(null,"No Account, Login FIrst");
        Login l = new Login();
        l.setVisible(true);
        this.dispose();
        }else
        {
        acc_fname.setText("" + sess.getFname());
        acc_lname.setText("" + sess.getLname());
        acc_uname.setText("" + sess.getUname());
        acc_type.setText("" + sess.getType());
        acc_phone.setText("" + sess.getPhone());
        acc_id.setText("" + sess.getUid());
        }*/
        
        
        Session sess = Session.getInstance();
        if (sess.getUid() == 0) {
            JOptionPane.showMessageDialog(null, "No Account, Login FIrst");
            Login l = new Login();
            l.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_formWindowActivated

    private void RemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseClicked
        Remove.setEnabled(false);
        Select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_RemoveMouseClicked

    private void RemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseEntered
        Remove.setBackground(h);
    }//GEN-LAST:event_RemoveMouseEntered

    private void RemoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseExited
        Remove.setBackground(d);
    }//GEN-LAST:event_RemoveMouseExited

    private void SelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseClicked
        //         imageuploadjava.txt
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/userimages/" + selectedFile.getName();
                path = selectedFile.getAbsolutePath();

                if (FileExistenceChecker(path) == 1) {
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path = "";
                } else {
                    image.setIcon(ResizeImage(path, null, image));
                    Select.setEnabled(false);
                    Remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_SelectMouseClicked

    private void SelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseEntered
        Select.setBackground(h);
    }//GEN-LAST:event_SelectMouseEntered

    private void SelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseExited
        Select.setBackground(d);
    }//GEN-LAST:event_SelectMouseExited

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
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new accountDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Fname;
    private javax.swing.JPanel Header;
    public javax.swing.JTextField Lname;
    public javax.swing.JTextField MR_username;
    private javax.swing.JPanel Main;
    public javax.swing.JPanel Remove;
    public javax.swing.JPanel Select;
    private javax.swing.JPanel confirm;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel logout;
    public javax.swing.JTextField phone;
    // End of variables declaration//GEN-END:variables
}
