/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites.UserAccount;

import Quickbites.Customer.Customer;
import Quickbites.Employee.Employee;
import Quickbites.Database.DatabaseConnection;
import Quickbites.Role.Role;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author srikr
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> userAccountList;
    int uid;
    int eid;
    int rid;

    public UserAccountDirectory() {
        userAccountList = new ArrayList();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }
    
    public UserAccount authenticateUser(String username, String password){
        for (UserAccount ua : userAccountList)
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)){
                return ua;
            }
        return null;
    }
    
    public UserAccount createUserAccount(String name, String username, String password, Employee employee, Role role){
        UserAccount userAccount = new UserAccount();
        userAccount.setName(name);
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setEmployee(employee);
        userAccount.setRole(role);
        userAccountList.add(userAccount);
        return userAccount;
    }
    
     public void updateUserAccount(UserAccount user,String name,String username, String password){
       
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
    }
    
    public void deleteUserAccount(UserAccount user){
        userAccountList.remove(user);
    }
    
    
    public boolean checkIfUsernameIsUnique(String username){
        for (UserAccount ua : userAccountList){
            if (ua.getUsername().equals(username))
                return false;
        }
        return true;
    }
    
    public void deleteUseraccount(String username){
        for(int i=0;i<userAccountList.size();i++){
            if(userAccountList.get(i).getUsername()==username){
                userAccountList.remove(i);
                try{
                    Connection con = DatabaseConnection.getCon();
                    Statement st = con.createStatement();
                    String query = "USE fooddeliver";
                    st.executeQuery(query);
                    query = "DELETE FROM useraccount WHERE userName = '"+username+"'";
                    st.executeUpdate(query);
                }catch(Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
    }
    
    public ArrayList<UserAccount> getUseraccountDatafromDB(){
        ArrayList<UserAccount> UserAccountData = new ArrayList();
        try{
            Connection con = DatabaseConnection.getCon();
            String sql = " SELECT * FROM useraccount";
            String sql1 = "SELECT * FROM employee where Employeeid in"
                    + "(Select Employeeid from useraccountemployee where useraccountid =?)";
            String sql2 = "SELECT * FROM role where Roleid in"
                    + "(Select Roleid from useraccountrole where useraccountid =?)";
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps1 = con.prepareStatement(sql1);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            while(rs.next()){
                UserAccount u = new UserAccount();
                uid = rs.getInt("useraccountid");
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                //if(rs.getString("roletype").equals("RestaurantAdmin"))
                //u.setRole();
                ps1.setInt(1, uid);
                ps2.setInt(1, uid);
                if(rs1.next()){
                    eid=rs1.getInt("Employeeid");
                    u.setEmployee(eid,rs1.getString("name"));
                }
                if(rs2.next()){
                    rid=rs2.getInt("Roleid");
                    u.setRole(rid,rs1.getString("type"));
                }
                UserAccountData.add(u);
                }
            }catch(Exception e){
                    System.out.print(e.getMessage());
            }
            return UserAccountData;
    }
    
    public UserAccount addUserAccount(UserAccount c){
        userAccountList.add(c);
        try{
            Connection con = DatabaseConnection.getCon();
            Statement st = con.createStatement();
            String query = "USE fooddeliver";
            String query1;
            String query2;
            st.executeUpdate(query);
            query = "INSERT INTO useraccount (name,username,password)"
                    + "VALUES (?,?,?))";
            query1 = "INSERT INTO useraccountemployee (useraccountid,Employeeid)"
                    + "VALUES (?,?))";
            query2 = "INSERT INTO useraccountrole (useraccountid,Roleid)"
                    + "VALUES (?,?))";
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            ps=con.prepareStatement(query);
            ps1=con.prepareStatement(query1);
            ps2=con.prepareStatement(query2);
            ps.setString(1, c.getName());
            ps.setString(2,c.getUsername());
            ps.setString(3, c.getPassword());
            ps.execute();
            ps1.setInt(1,uid);
            ps1.setInt(2,eid);
            ps2.setInt(1,uid);
            ps2.setInt(2,rid);
            ps1.execute();
            ps2.execute();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return c;
    }
    
}