/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quickbites.DeliveryMan;

import Quickbites.Customer.Customer;
import Quickbites.Database.DatabaseConnection;
import Quickbites.UserAccount.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author srikr
 */
public class DeliveryManDirectory {
 
    private ArrayList<DeliveryMan> deliveryManDirectory;
    private DeliveryMan deliveryMan;
    
    public DeliveryManDirectory(){
        this.deliveryManDirectory =  new ArrayList<DeliveryMan>();
    }

    public ArrayList<DeliveryMan> getDeliveryManDirectory() {
        return deliveryManDirectory;
    }

    public void setDeliveryManDirectory(ArrayList<DeliveryMan> deliveryManDirectory) {
        this.deliveryManDirectory = deliveryManDirectory;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
    
    public DeliveryMan createUserAccount(String username){
        deliveryMan = new DeliveryMan(username);
        deliveryManDirectory.add(deliveryMan);
        return deliveryMan;
    }
    
    
    public void deleteDeliveryMan(String username){
        for(int i=0;i<deliveryManDirectory.size();i++){
            if(deliveryManDirectory.get(i).getUserName()==username){
                deliveryManDirectory.remove(i);
                try{
                    Connection con = DatabaseConnection.getCon();
                    Statement st = con.createStatement();
                    String query = "USE fooddeliver";
                    st.executeQuery(query);
                    query = "DELETE FROM deliverymanorders WHERE Deliverymanid ="
                            + "(Select Deliverymanid from deliveryman where userName='"+username+"')";
                    st.executeUpdate(query);
                    query = "DELETE FROM deliveryman WHERE userName = '"+username+"'";
                    st.executeUpdate(query);
                }catch(Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
    
    }
    
    public ArrayList<DeliveryMan> getDeliveryManDatafromDB(){
        ArrayList<DeliveryMan> DeliveryManData = new ArrayList();
        try{
            Connection con = DatabaseConnection.getCon();
            String sql = " SELECT * FROM deliveryman";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                sql = " SELECT * FROM deliverymanorders where Deliverymanid ="
                                + "(Select Deliverymanid from deliveryman where name='"+rs.getString("name")+"')";
                PreparedStatement ps1 = con.prepareStatement(sql);
                ResultSet rs1 = ps1.executeQuery();
                DeliveryMan d = new DeliveryMan();
                d.setName(rs.getString("name"));
                d.setUserName(rs.getString("userName"));
                d.setAddress(rs.getString("address"));
                d.setNumber(rs.getString("phoneno"));
                DeliveryManData.add(d);
                }
            }catch(Exception e){
                    System.out.print(e.getMessage());
            }
            return DeliveryManData;
    }
    
    public DeliveryMan addDeliveryMan(DeliveryMan d){
        deliveryManDirectory.add(d);
        try{
            Connection con = DatabaseConnection.getCon();
            Statement st = con.createStatement();
            String query = "USE fooddeliver";
            st.executeUpdate(query);
            query = "INSERT INTO deliveryman (name,userName,address,phoneno)"
                    + "VALUES (?,?,?,?))";
            PreparedStatement ps = null;
            ps=con.prepareStatement(query);
            ps.setString(1, d.getName());
            ps.setString(2,d.getUserName());
            ps.setString(3, d.getAddress());
            ps.setString(4,d.getNumber());
            ps.execute();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return d;
    }
    
}
