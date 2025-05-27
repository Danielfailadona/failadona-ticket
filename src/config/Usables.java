/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import static admin.CU_Admin.getHeightFromWidth;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author DANIEL FAILADONA
 */
public class Usables {
    
    //===============================================================
    //                      IMAGE RESIZER
    //===============================================================
    
    //===============================================================
    //                      HOW TO USE:
    //    setImageToLabel(Pic1,"src/userimages/profile_01.png"); 
    //    setImageToLabel(Pic2,"src/userimages/profile_02.png");
    //===============================================================

    public void setImageToLabel(JLabel label, String imagePath) {
        try {
            if (imagePath != null && !imagePath.isEmpty()) {
                int newHeight = getHeightFromWidth(imagePath, label.getWidth());
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage().getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(image));
            } else {
                label.setIcon(null); // Clear the label if path is invalid
            }
        } catch (Exception e) {
            System.out.println("Failed to set image on label: " + e.getMessage());
            label.setIcon(null);
        }
    }
    
}
