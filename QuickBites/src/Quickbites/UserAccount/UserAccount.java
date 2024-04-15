/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites.UserAccount;

import Quickbites.Employee.Employee;
import Quickbites.Role.Role;
import Quickbites.Role.Role.RoleType;
import Quickbites.WorkQueue.WorkQueue;

/**
 *
 * @author srikr
 */
public class UserAccount {
    
    private String name;
    private String username;
    private String password;
    private Employee employee;
    private Role role;
    private WorkQueue workQueue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    public UserAccount() {
        workQueue = new WorkQueue();
    }
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public void setEmployee(int employeeid,String name) {
        Employee e =new Employee();
        e.setName(name);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(int roleid,String type) {
        if(type.equals("RestaurantAdmin")){
            RoleType r = RoleType.RestaurantAdmin;
        }else if(type.equals("Delivery")){
            RoleType r = RoleType.DeliveryMan;
        }else if(type.equals("Sysadmin")){
            RoleType r = RoleType.SysAdmin;
        }else if(type.equals("Customer")){
            RoleType r = RoleType.Customer;
        }
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    
    
    @Override
    public String toString() {
        return username;
    }
    
}
