/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Startups.Login;
import java.awt.Color;
import Startups.StartupPanel;
import config.Session;
import config.Usables;
import config.dbConnect;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;
public class AdminDashboard extends javax.swing.JFrame {

    private Color H;
    Color h = new Color(145, 101, 88);
    private Color D;
    Color d = new Color(181, 126, 110);
    public final Usables use = new Usables();
    
    public AdminDashboard() {
        initComponents();
    }
    
    
    public void logEvent(int userId, String username, String action) {
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try {
            String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);

            /*            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setString(4, userType);*/
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
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
            System.out.println("desiredWidth: " + desiredWidth);
            System.out.println("1");
            File imageFile = new File(imagePath);
            System.out.println("imagePath: " + imagePath);
            System.out.println("imageFile: " + imageFile);
            BufferedImage image = ImageIO.read(imageFile);
            System.out.println("image: " + image);
            System.out.println("2");

            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            System.out.println("3");

            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            System.out.println("4");

            return newHeight;
        } catch (IOException ex) {
//            System.out.println("imagePath!"+imagePath); //Image path exists,  Must_Debug:False
//            System.out.println("No image found!");
            System.out.println("" + ex);
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
        Navigation = new javax.swing.JPanel();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        acc_fname = new javax.swing.JLabel();
        acc_lname = new javax.swing.JLabel();
        acc_uname = new javax.swing.JLabel();
        acc_type = new javax.swing.JLabel();
        acc_phone = new javax.swing.JLabel();
        acc_id = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        users1 = new javax.swing.JPanel();
        Change_Pass = new javax.swing.JLabel();
        users = new javax.swing.JPanel();
        usersImage = new javax.swing.JLabel();
        users2 = new javax.swing.JPanel();
        Change_Pass1 = new javax.swing.JLabel();
        users3 = new javax.swing.JPanel();
        Add_Recovery = new javax.swing.JLabel();
        users4 = new javax.swing.JPanel();
        Change_Pass2 = new javax.swing.JLabel();
        users5 = new javax.swing.JPanel();
        Change_Pass3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        users6 = new javax.swing.JPanel();
        Change_Pass4 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Main.setBackground(new java.awt.Color(158, 98, 80));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(181, 126, 110));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(255, 255, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin Dashboard");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1310, 40));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 100));

        Navigation.setBackground(new java.awt.Color(124, 77, 63));
        Navigation.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(255, 255, 255)));
        Navigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setBackground(new java.awt.Color(181, 126, 110));
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
        logout.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Logout");
        logout.add(jLabel10);
        jLabel10.setBounds(0, 10, 130, 22);

        Navigation.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 493, 130, 40));

        acc_fname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_fname.setForeground(new java.awt.Color(255, 255, 255));
        acc_fname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_fname.setText("First Name");
        Navigation.add(acc_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 300, 30));

        acc_lname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_lname.setForeground(new java.awt.Color(255, 255, 255));
        acc_lname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_lname.setText("Last Name");
        Navigation.add(acc_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 300, 30));

        acc_uname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_uname.setForeground(new java.awt.Color(255, 255, 255));
        acc_uname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_uname.setText("User Name");
        Navigation.add(acc_uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 300, 30));

        acc_type.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_type.setForeground(new java.awt.Color(255, 255, 255));
        acc_type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_type.setText("Type");
        Navigation.add(acc_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 300, 30));

        acc_phone.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_phone.setForeground(new java.awt.Color(255, 255, 255));
        acc_phone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_phone.setText("Phone");
        Navigation.add(acc_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 300, 30));

        acc_id.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_id.setForeground(new java.awt.Color(255, 255, 255));
        acc_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id.setText("ID");
        Navigation.add(acc_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 300, 30));
        Navigation.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -90, 310, 160));

        jPanel1.setBackground(new java.awt.Color(181, 126, 110));
        jPanel1.setLayout(null);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Users.png"))); // NOI18N
        jPanel1.add(image);
        image.setBounds(10, 10, 190, 170);

        Navigation.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 210, 190));

        Main.add(Navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 300, 550));

        users1.setBackground(new java.awt.Color(181, 126, 110));
        users1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users1MouseExited(evt);
            }
        });
        users1.setLayout(null);

        Change_Pass.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Change_Pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Change_Pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Lock.png"))); // NOI18N
        users1.add(Change_Pass);
        Change_Pass.setBounds(5, 5, 160, 110);

        Main.add(users1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 170, 120));

        users.setBackground(new java.awt.Color(181, 126, 110));
        users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usersMouseExited(evt);
            }
        });
        users.setLayout(null);

        usersImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Users.png"))); // NOI18N
        users.add(usersImage);
        usersImage.setBounds(30, 10, 110, 100);

        Main.add(users, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 170, 120));

        users2.setBackground(new java.awt.Color(181, 126, 110));
        users2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users2MouseExited(evt);
            }
        });
        users2.setLayout(null);

        Change_Pass1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Change_Pass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Change_Pass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-activity-history-64.png"))); // NOI18N
        users2.add(Change_Pass1);
        Change_Pass1.setBounds(5, 5, 160, 110);

        Main.add(users2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 170, 120));

        users3.setBackground(new java.awt.Color(181, 126, 110));
        users3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users3MouseExited(evt);
            }
        });
        users3.setLayout(null);

        Add_Recovery.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Add_Recovery.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Add_Recovery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Keys.png"))); // NOI18N
        users3.add(Add_Recovery);
        Add_Recovery.setBounds(5, 5, 160, 110);

        Main.add(users3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 170, 120));

        users4.setBackground(new java.awt.Color(181, 126, 110));
        users4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users4MouseExited(evt);
            }
        });
        users4.setLayout(null);

        Change_Pass2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Change_Pass2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Change_Pass2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-movies-folder-48.png"))); // NOI18N
        users4.add(Change_Pass2);
        Change_Pass2.setBounds(5, 5, 160, 110);

        Main.add(users4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 110, 170, 120));

        users5.setBackground(new java.awt.Color(181, 126, 110));
        users5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users5MouseExited(evt);
            }
        });
        users5.setLayout(null);

        Change_Pass3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Change_Pass3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Change_Pass3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-money-48.png"))); // NOI18N
        users5.add(Change_Pass3);
        Change_Pass3.setBounds(5, 5, 160, 110);

        Main.add(users5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 170, 120));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Users");
        Main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 160, 20));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Change Pass");
        Main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 160, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Logs");
        Main.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 160, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Add Password");
        Main.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 240, 160, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Recovery");
        Main.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 260, 160, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Manage Movies");
        Main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 240, 160, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("See Sold Movies");
        Main.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 160, 20));

        users6.setBackground(new java.awt.Color(181, 126, 110));
        users6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users6MouseExited(evt);
            }
        });
        users6.setLayout(null);

        Change_Pass4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Change_Pass4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Change_Pass4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-payment-history-50.png"))); // NOI18N
        users6.add(Change_Pass4);
        Change_Pass4.setBounds(5, 5, 160, 110);

        Main.add(users6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 170, 120));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("See Transaction");
        Main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 450, 160, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Logs");
        Main.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 466, 160, -1));

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
        dbConnect connector = new dbConnect();
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        int userId = 0;
        String uname = null;
        try {
            String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
            PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                uname = resultSet.getString("u_username");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }

        logEvent(userId, uname, "Logged Out");
        StartupPanel sp = new StartupPanel();
        sp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void usersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseClicked
        U_Admin ua = new U_Admin();
        ua.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_usersMouseClicked

    private void usersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseEntered
        users.setBackground(h);
    }//GEN-LAST:event_usersMouseEntered

    private void usersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseExited
        users.setBackground(d);
    }//GEN-LAST:event_usersMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
       if(sess.getUid() == 0)
       {
           JOptionPane.showMessageDialog(null,"No Account, Login FIrst");
           Login l = new Login();
           l.setVisible(true);
           this.dispose();
       }else
       {
           acc_fname.setText("First Name: " + sess.getFname());
           acc_lname.setText("Last Name: " + sess.getLname());
           acc_uname.setText("User Name: " + sess.getUname());
           acc_type.setText("Type: " + sess.getType());
           acc_phone.setText("Phone: " + sess.getPhone());
           acc_id.setText("ID: " + sess.getUid());
           
           try 
           {
               dbConnect dbc = new dbConnect();
               ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'");
               System.out.println("1");
               if (rs.next()) 
               {
                   System.out.println("2");
                   String image1 = rs.getString("u_image");
                   String id = rs.getString("u_id");
                   System.out.println("image: "+image1);
                   System.out.println("UserID: "+id);
                   
                    image.setIcon(ResizeImage(rs.getString("u_image"), null, image));
                    oldpath = rs.getString("u_image");
                    path = rs.getString("u_image");
                    destination = rs.getString("u_image");
               }

           } catch (SQLException ex) {
               System.out.println("" + ex);
           }
       }
    }//GEN-LAST:event_formWindowActivated

    private void users1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users1MouseClicked
    Admin_ChangePass acp = new Admin_ChangePass();
    acp.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_users1MouseClicked

    private void users1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users1MouseEntered
        users1.setBackground(h);
    }//GEN-LAST:event_users1MouseEntered

    private void users1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users1MouseExited
        users1.setBackground(d);
    }//GEN-LAST:event_users1MouseExited

    private void users2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users2MouseClicked
        Logs_Admin la = new Logs_Admin();
        la.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_users2MouseClicked

    private void users2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users2MouseEntered
        users2.setBackground(h);
    }//GEN-LAST:event_users2MouseEntered

    private void users2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users2MouseExited
        users2.setBackground(d);
    }//GEN-LAST:event_users2MouseExited

    private void users3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users3MouseClicked
    A_Add_Recovery aar = new A_Add_Recovery();
    aar.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_users3MouseClicked

    private void users3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users3MouseEntered
        users3.setBackground(h);
    }//GEN-LAST:event_users3MouseEntered

    private void users3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users3MouseExited
        users3.setBackground(d);
    }//GEN-LAST:event_users3MouseExited

    private void users4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users4MouseClicked
        productForm pf = new productForm();
        pf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_users4MouseClicked

    private void users4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users4MouseEntered
        users4.setBackground(h);
    }//GEN-LAST:event_users4MouseEntered

    private void users4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users4MouseExited
        users4.setBackground(d);
    }//GEN-LAST:event_users4MouseExited

    private void users5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users5MouseClicked
        HowManySold hms = new HowManySold();
        hms.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_users5MouseClicked

    private void users5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users5MouseEntered
        users5.setBackground(h);
    }//GEN-LAST:event_users5MouseEntered

    private void users5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users5MouseExited
        users5.setBackground(d);
    }//GEN-LAST:event_users5MouseExited

    private void users6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users6MouseClicked
        TransactionLogs tl = new TransactionLogs();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_users6MouseClicked

    private void users6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_users6MouseEntered

    private void users6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_users6MouseExited

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add_Recovery;
    private javax.swing.JLabel Change_Pass;
    private javax.swing.JLabel Change_Pass1;
    private javax.swing.JLabel Change_Pass2;
    private javax.swing.JLabel Change_Pass3;
    private javax.swing.JLabel Change_Pass4;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel Navigation;
    private javax.swing.JLabel acc_fname;
    private javax.swing.JLabel acc_id;
    private javax.swing.JLabel acc_lname;
    private javax.swing.JLabel acc_phone;
    private javax.swing.JLabel acc_type;
    private javax.swing.JLabel acc_uname;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel users;
    private javax.swing.JPanel users1;
    private javax.swing.JPanel users2;
    private javax.swing.JPanel users3;
    private javax.swing.JPanel users4;
    private javax.swing.JPanel users5;
    private javax.swing.JPanel users6;
    public javax.swing.JLabel usersImage;
    // End of variables declaration//GEN-END:variables
}
