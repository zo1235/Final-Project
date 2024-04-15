/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites.Role;

import Quickbites.EcoSystem;
import Quickbites.UserAccount.UserAccount;
import UserInterface.CustomerRole.CustomerAreaPanel;
import javax.swing.JPanel;

/**
 *
 * @author srikr
 */
public class CustomerRole extends Role{
    
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, EcoSystem ecosystem) {
        return new CustomerAreaPanel(userProcessContainer, account,ecosystem);
    }
    
    
}
