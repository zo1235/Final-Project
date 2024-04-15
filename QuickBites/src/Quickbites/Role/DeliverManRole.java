/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites.Role;

import Business.EcoSystem;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import UserInterface.DeliveryManRole.DeliveryManWorkAreaPanel;

/**
 *
 * @author srikr
 */
public class DeliverManRole extends Role {
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, EcoSystem business) {
        return new DeliveryManWorkAreaPanel(userProcessContainer,account,business);//To change body of generated methods, choose Tools | Templates.
    }
    
    
}
