/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.Session;
import config.dbConnect;
import config.passwordHasher;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class CU_Admin extends javax.swing.JFrame {

    private Color H;
    Color h = new Color(51,51,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public CU_Admin() {
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


    
    public static String phone,usname;
    public boolean duplicateCheck()
    {
        dbConnect dbc = new dbConnect();
        String p = PhoneNum.getText().trim();
        String us = MR_username.getText().trim();
        
        try
        {
            String query = "SELECT * FROM tbl_accounts WHERE u_username='"+ us +"'OR u_phone='"+ p +"'";
            ResultSet resultSet = dbc.getData(query);
            if(resultSet.next())
            {
                phone = resultSet.getString("u_phone");
                if(phone.equals(p))
                {
                    JOptionPane.showMessageDialog(null, "Phone Number is Already Used");
                    PhoneNum.setText("");
                }
                
                usname = resultSet.getString("u_username");
                if(usname.equals(us))
                {
                    JOptionPane.showMessageDialog(null, "Username is Already Used");
                    MR_username.setText("");
                }
                return true;
            }else
            {
                return false;
            }
        }catch(SQLException ex)
        {
            System.out.println(""+ex);
            return false;
        }
    }
    
    
    
    public boolean updateCheck()
    {
        dbConnect dbc = new dbConnect();
        String u = UID.getText().trim();
        String p = PhoneNum.getText().trim();
        String us = MR_username.getText().trim();
        
        try
        {
            String query = "SELECT * FROM tbl_accounts WHERE (u_username='"+ us +"'OR u_phone='"+ p +"') AND u_id != '"+u+"'";
            ResultSet resultSet = dbc.getData(query);
            if(resultSet.next())
            {
                phone = resultSet.getString("u_phone");
                if(phone.equals(p))
                {
                    JOptionPane.showMessageDialog(null, "Phone Number is Already Used");
                    PhoneNum.setText("");
                }
                
                usname = resultSet.getString("u_username");
                if(usname.equals(us))
                {
                    JOptionPane.showMessageDialog(null, "Username is Already Used");
                    MR_username.setText("");
                }
                return true;
            }else
            {
                return false;
            }
        }catch(SQLException ex)
        {
            System.out.println(""+ex);
            return false;
        }
    }
    
    
    
    public void logEvent(int userId, String username, String action) 
    {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        New_Manager = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        clear = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Navigation = new javax.swing.JPanel();
        cancel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        check1 = new javax.swing.JCheckBox();
        MR_username = new javax.swing.JTextField();
        MR_passwordConfirm = new javax.swing.JPasswordField();
        MR_password = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        Lname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Fname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        PhoneNum = new javax.swing.JTextField();
        type = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        add = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        update = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        delete = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        UID = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        check2 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        Remove = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        Select = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        New_Manager.setBackground(new java.awt.Color(102, 102, 102));
        New_Manager.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(0, 0, 0));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add User");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 180, 40));

        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshMouseExited(evt);
            }
        });
        refresh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Refresh");
        refresh.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 90, 10));

        Header.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 30, -1, 30));

        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearMouseExited(evt);
            }
        });
        clear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Clear");
        clear.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 90, 10));

        Header.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, -1, 30));

        New_Manager.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 100));

        Navigation.setBackground(new java.awt.Color(51, 51, 51));
        Navigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelMouseExited(evt);
            }
        });
        cancel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cancel");
        cancel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 90, 10));

        Navigation.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, -1, 30));
        Navigation.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 90));
        Navigation.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 430));

        New_Manager.add(Navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 270, 560));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Select Account Type:");
        New_Manager.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 150, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Password:");
        New_Manager.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, 80, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Confirm Password:");
        New_Manager.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, 140, 30));

        check1.setBackground(new java.awt.Color(255, 255, 255));
        check1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        check1.setForeground(new java.awt.Color(255, 255, 255));
        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });
        New_Manager.add(check1, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 430, -1, 30));

        MR_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MR_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MR_usernameActionPerformed(evt);
            }
        });
        New_Manager.add(MR_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 330, 30));

        MR_passwordConfirm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MR_passwordConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MR_passwordConfirmActionPerformed(evt);
            }
        });
        New_Manager.add(MR_passwordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 304, 30));

        MR_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MR_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MR_passwordActionPerformed(evt);
            }
        });
        New_Manager.add(MR_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 304, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Last Name:");
        New_Manager.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 80, 30));

        Lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LnameActionPerformed(evt);
            }
        });
        New_Manager.add(Lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 330, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("  First Name:");
        New_Manager.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 90, 30));

        Fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FnameActionPerformed(evt);
            }
        });
        New_Manager.add(Fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 330, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Phone Number:");
        New_Manager.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 530, 130, 30));

        PhoneNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PhoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneNumActionPerformed(evt);
            }
        });
        New_Manager.add(PhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, 330, 30));

        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee", "Admin" }));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        New_Manager.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, 330, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Username:");
        New_Manager.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 80, 30));

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Active" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        New_Manager.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 380, 330, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Select Account Status:");
        New_Manager.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 160, 30));

        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addMouseExited(evt);
            }
        });
        add.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Add");
        add.add(jLabel11);
        jLabel11.setBounds(0, 10, 90, 10);

        New_Manager.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 620, 90, 30));

        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateMouseExited(evt);
            }
        });
        update.setLayout(null);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Update");
        update.add(jLabel17);
        jLabel17.setBounds(0, 10, 90, 10);

        New_Manager.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 620, 90, 30));

        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteMouseExited(evt);
            }
        });
        delete.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Delete");
        delete.add(jLabel18);
        jLabel18.setBounds(0, 10, 90, 10);

        New_Manager.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 620, 90, 30));

        UID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UID.setEnabled(false);
        UID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UIDActionPerformed(evt);
            }
        });
        New_Manager.add(UID, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 330, 30));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("User ID:");
        New_Manager.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 90, 30));

        check2.setBackground(new java.awt.Color(255, 255, 255));
        check2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        check2.setForeground(new java.awt.Color(255, 255, 255));
        check2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check2ActionPerformed(evt);
            }
        });
        New_Manager.add(check2, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 480, -1, 30));

        jPanel1.setLayout(null);
        jPanel1.add(image);
        image.setBounds(10, 10, 190, 170);

        New_Manager.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 210, 190));

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

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Remove");
        Remove.add(jLabel21);
        jLabel21.setBounds(0, 10, 90, 10);

        New_Manager.add(Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 340, 90, 30));

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

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Select");
        Select.add(jLabel22);
        jLabel22.setBounds(0, 10, 90, 10);

        New_Manager.add(Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 340, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(New_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(New_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        U_Admin ua = new U_Admin();
        ua.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
        cancel.setBackground(h);
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        cancel.setBackground(d);
    }//GEN-LAST:event_cancelMouseExited

    private void MR_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MR_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MR_usernameActionPerformed

    private void MR_passwordConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MR_passwordConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MR_passwordConfirmActionPerformed

    private void MR_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MR_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MR_passwordActionPerformed

    private void LnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LnameActionPerformed

    private void FnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FnameActionPerformed

    private void PhoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneNumActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        dbConnect connector = new dbConnect();
        int userId = 0;
        String uname2 = null;
        String fn = Fname.getText().trim();
        String ln = Lname.getText().trim();
        String uname = MR_username.getText().trim();
        String pass = new String(MR_password.getPassword()).trim();
        String Cpass = new String(MR_passwordConfirm.getPassword()).trim();
        String p = PhoneNum.getText().trim();
        String at = type.getSelectedItem().toString().trim();
        String s = status.getSelectedItem().toString().trim();
        String sq = "";
        String sa   = "";

        if(uname.isEmpty() || pass.isEmpty() || Cpass.isEmpty() || ln.isEmpty() || fn.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Fill All Boxes");

        }else if(!pass.equals(Cpass))
        {
            JOptionPane.showMessageDialog(null, "Password Does Not Match");
            //System.out.println("Password ["+password+"] Length: "+password.length());
            //System.out.println("Confirm Password ["+Cpassword+"] Length: "+Cpassword.length());
        }else if(!p.matches("\\d+"))
        {
            JOptionPane.showMessageDialog(null, "Phone Must Only Contain Numbers");
        }else if(pass.length() <= 7)
        {
            JOptionPane.showMessageDialog(null, "Password Must be Exactly 8 Characters Long");
        }else if(p.length() > 15 || p.length() < 11)
        {
            JOptionPane.showMessageDialog(null, "Invalid Phone num");
        }else if(duplicateCheck())
        {
//            System.out.println("Duplicate Exists");
            JOptionPane.showMessageDialog(null, "Duplicate Exists");
        }else
        {
            try
            {
                /*                String query = "SELECT * FROM tbl_accounts WHERE u_id='" + sess.getUid() + "'";
                ResultSet rs = dbc.getData(query);
                if (rs.next())
                {*/
                String npass = passwordHasher.hashPassword("u_password");

                if(dbc.insertData("INSERT INTO tbl_accounts (u_fname, u_lname, u_username, u_type, u_password, u_phone, u_status, u_image, security_question, security_answer) "
                    + "VALUES ('" + fn + "', '" + ln + "', '" + uname + "', '" + at + "','" + npass + "', '" + p + "',  '" + s + "',  '" + destination + "',  '" + sq + "',  '" + sa + "')"))
                {
                    




                    try 
                    {
                        String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
                        PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

                        ResultSet resultSet = pstmt.executeQuery();

                        if (resultSet.next()) 
                        {
                            userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                            uname2 = resultSet.getString("u_username");
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Exception: " + ex);
                    }



                    logEvent(userId, uname2, "Admin Added Account: "+uname);


                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING );
                    JOptionPane.showMessageDialog(null, "Registered succesfully!");
                    U_Admin ua = new U_Admin();
                    ua.setVisible(true);
                    this.dispose();
                }else
                {
                    JOptionPane.showMessageDialog(null, "An error occured");
                    System.out.println("Dan, Error occured in line: 757");
                    U_Admin ua = new U_Admin();
                    ua.setVisible(true);
                    this.dispose();
                }
//                }
            } catch(IOException  | NoSuchAlgorithmException ex)
            {
                System.out.println("" + ex);
            }
        }
    }//GEN-LAST:event_addMouseClicked

    private void addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseEntered
        add.setBackground(h);
    }//GEN-LAST:event_addMouseEntered

    private void addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseExited
        add.setBackground(d);
    }//GEN-LAST:event_addMouseExited

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshMouseClicked

    private void refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshMouseEntered

    private void refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshMouseExited

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        dbConnect connector = new dbConnect();
        int userId = 0;
        String uname2 = null;
        String u = UID.getText().trim();
        String fn = Fname.getText().trim();
        String ln = Lname.getText().trim();
        String uname = MR_username.getText().trim();
        String pass = new String(MR_password.getPassword()).trim();
        String Cpass = new String(MR_passwordConfirm.getPassword()).trim();
        String p = PhoneNum.getText().trim();
        String at = type.getSelectedItem().toString().trim();
        String s = status.getSelectedItem().toString().trim();
        

        if(uname.isEmpty() || ln.isEmpty() || fn.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Fill All Boxes");

        }else if(!p.matches("\\d+"))
        {
            JOptionPane.showMessageDialog(null, "Phone Must Only Contain Numbers");
        }else if(p.length() > 15 || p.length() < 11)
        {
            JOptionPane.showMessageDialog(null, "Invalid Phone num");
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
                    
                    dbc.updateData("UPDATE tbl_accounts SET u_fname = '" + fn + "', u_lname = '" + ln + "', u_username = '" + uname + "',"
                        + " u_password = '"+npass+"', u_phone = '"+p+"', u_type = '"+at+"', u_status = '"+s+"', u_image = '"+destination+"' WHERE u_id = '"+u+"'");
                    
                    
                    
                    
                    try 
                    {
                        String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
                        PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

                        ResultSet resultSet = pstmt.executeQuery();

                        if (resultSet.next()) 
                        {
                            userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                            uname2 = resultSet.getString("u_username");
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Exception: " + ex);

                    }


                    logEvent(userId, uname2, "Admin Updated Account: "+uname);
                    
                    
                    if(destination.isEmpty())
                    {
                        File existingFile = new File(oldpath);
                        if(existingFile.exists())
                        {
                            existingFile.delete();
                        }
                    }else
                    {
                        if(!(oldpath.equals(path)))
                        {
                            imageUpdater(oldpath,path);
                        }
                    }
                    
                    U_Admin ua = new U_Admin();
                    ua.setVisible(true);
                    this.dispose();
                }
            } catch (SQLException ex) {
                System.out.println("" + ex);
            }
        }

    }//GEN-LAST:event_updateMouseClicked

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered
        update.setBackground(h);
    }//GEN-LAST:event_updateMouseEntered

    private void updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseExited
        update.setBackground(d);
    }//GEN-LAST:event_updateMouseExited

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteMouseClicked

    private void deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteMouseEntered

    private void deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteMouseExited

    private void clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_clearMouseClicked

    private void clearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_clearMouseEntered

    private void clearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_clearMouseExited

    private void UIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UIDActionPerformed

    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        boolean isSelected = check1.isSelected();

        if (isSelected) {
            MR_password.setEchoChar((char)0);
        } else {
            MR_password.setEchoChar('*');
        }
    }//GEN-LAST:event_check1ActionPerformed

    private void check2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check2ActionPerformed
        boolean isSelected = check2.isSelected();

        if (isSelected) {
            MR_passwordConfirm.setEchoChar((char)0);
        } else {
            MR_passwordConfirm.setEchoChar('*');
        }
    }//GEN-LAST:event_check2ActionPerformed

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
            java.util.logging.Logger.getLogger(CU_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CU_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CU_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CU_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CU_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Fname;
    private javax.swing.JPanel Header;
    public javax.swing.JTextField Lname;
    public javax.swing.JPasswordField MR_password;
    public javax.swing.JPasswordField MR_passwordConfirm;
    public javax.swing.JTextField MR_username;
    private javax.swing.JPanel Navigation;
    private javax.swing.JPanel New_Manager;
    public javax.swing.JTextField PhoneNum;
    public javax.swing.JPanel Remove;
    public javax.swing.JPanel Select;
    public javax.swing.JTextField UID;
    public javax.swing.JPanel add;
    private javax.swing.JPanel cancel;
    private javax.swing.JCheckBox check1;
    private javax.swing.JCheckBox check2;
    private javax.swing.JPanel clear;
    private javax.swing.JPanel delete;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel refresh;
    public javax.swing.JComboBox<String> status;
    public javax.swing.JComboBox<String> type;
    public javax.swing.JPanel update;
    // End of variables declaration//GEN-END:variables
}
