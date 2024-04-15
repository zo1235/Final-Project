/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites;

import Quickbites.Employee.Employee;
import Quickbites.Role.SystemAdminRole;
import Quickbites.UserAccount.UserAccount;

/**
 *
 * @author srikr
 */
public class ConfigureASystem {
        
    public static EcoSystem configure(){
        
        EcoSystem system = EcoSystem.getInstance();
                
        Employee employee = system.getEmployeeDirectory().createEmployee("SA");
        
        UserAccount ua = system.getUserAccountDirectory().createUserAccount
        ("SysAdmin","sysadmin", "sysadmin", employee, new SystemAdminRole());
        
        return system;
    }
}
