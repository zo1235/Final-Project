/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites.Role;

import Quickbites.EcoSystem;
import Quickbites.UserAccount.UserAccount;
import UserInterface.RestaurantAdminRole.AdminWorkAreaPanel;
import javax.swing.JPanel;

/**
 *
 * @author srikr
 */
public class AdminRole extends Role{
    
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, EcoSystem business) {
        return new AdminWorkAreaPanel(userProcessContainer, account, business);
    }

    
}
