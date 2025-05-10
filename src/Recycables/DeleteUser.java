/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recycables;



/**
 *
 * @author DANIEL FAILADONA
 */
public class DeleteUser {
    
}


//import config.Session;
//import config.dbConnect;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.swing.JOptionPane;











//private void deleteUser()         
//    {
//        dbConnect dbc = new dbConnect();
//        Session sess = Session.getInstance();
//        dbConnect connector = new dbConnect();
////        int userId = 0;
//        String uname3 = null;
//        String uname2 = null;
//        String uname = null;
//        
//        
//        int selectedRow = account_table.getSelectedRow();
//        if (selectedRow == -1) 
//        {
//            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
//            return;
//        }
//
//        int userId = (int) account_table.getValueAt(selectedRow, 0);
//        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
//
//        if (confirm == JOptionPane.YES_OPTION) 
//        {
//            try 
//            {
//                String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + userId + "'";
//                PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);
//
//                ResultSet resultSet = pstmt.executeQuery();
//
//                if (resultSet.next()) 
//                {
//                    userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
//                    uname2 = resultSet.getString("u_username");
//                }
//            } catch (SQLException ex) 
//            {
//                System.out.println("SQL Exception: " + ex);
//            }
//
//            logEvent(userId, uname2, "User Added Account: " + uname2);
//            //for each letter in username, record all in variable >> record variable into database
//            // experimental feature: instead of fully deleting from databse, it would just be labled as deleted for recovery purposes
//            
//            
//            
//            String sql = "DELETE FROM tbl_accounts WHERE u_id=?";
//
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/theater_db", "root", "");
//            PreparedStatement pst = con.prepareStatement(sql)) 
//            {
//
//                pst.setInt(1, userId);
//                int affectedRows = pst.executeUpdate();
//
//                if (affectedRows > 0) 
//                {
//                    JOptionPane.showMessageDialog(this, "User Deleted Successfully!");
//                    loadUsersData();  // Ensure this method exists
//                    
//                    try 
//                    {
//                        String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
//                        PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);
//
//                        ResultSet resultSet = pstmt.executeQuery();
//
//                        if (resultSet.next()) 
//                        {
//                            userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
//                            uname2 = resultSet.getString("u_username");
//                            
//                            /*try
//                            {
//                            String query3 = "SELECT * FROM tbl_logs WHERE u_id = '" + selectedRow + "'";
//                            PreparedStatement pstmt2 = connector.getConnection().prepareStatement(query3);
//                            
//                            ResultSet resultSet2 = pstmt2.executeQuery();
//                            
//                            if (resultSet2.next())
//                            {
//                            uname = resultSet2.getString("u_username");
//                            System.out.println("" + uname);
//                            uname3 = uname2;
//                            System.out.println("" + uname3);
//                            
//                            }
//                            } catch (SQLException ex)
//                            {
//                            System.out.println("SQL Exception: " + ex);
//                            }*/
//                        }
//                    } catch (SQLException ex) 
//                    {
//                        System.out.println("SQL Exception: " + ex);
//
//                    }
//                    
//
//                    try {
//                        String query3 = "SELECT * FROM tbl_logs WHERE u_id = '" + selectedRow + "'";
//                        PreparedStatement pstmt = connector.getConnection().prepareStatement(query3);
//
//                        ResultSet resultSet = pstmt.executeQuery();
//
//                        if (resultSet.next()) {
//                            uname = resultSet.getString("u_username");
//                            System.out.println(""+uname);
//                            uname3 = uname;
//                            System.out.println("" + uname3);
//
//                        }
//                    } catch (SQLException ex) {
//                        System.out.println("SQL Exception: " + ex);
//                    }
//                    
//                    
//                    logEvent(userId, uname2, "User Deleted Account: " + uname3);
//                    
//                } else 
//                {
//                    JOptionPane.showMessageDialog(this, "No user found to delete.", "Deletion Failed", JOptionPane.WARNING_MESSAGE);
//                }
//
//            } catch (SQLException ex) 
//            {
//                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }