package com.model;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryDetailsDao {
	
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public DeliveryDetailsDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vsbdb", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeData(DeliveryDetails DeliveryDetails) {
        String sql = "INSERT INTO DeliveryDetails (delivery_date, delivery_address, delivery_status) VALUES (?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, DeliveryDetails.getDeliveryDate());
            pst.setString(2, DeliveryDetails.getDeliveryAddress());
            pst.setString(3, DeliveryDetails.getDeliveryStatus());
            
            
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieveDeliveryDetails() {
        String sql = "SELECT * FROM DeliveryDetails";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    
    DeliveryDetails  DeliveryDetails= null;
        String sql = "SELECT * FROM  DeliveryDetails WHERE order_id = ?";
        {
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
             DeliveryDetails = new  DeliveryDetails(
                    rs.getString("delivery_date"),
                    rs.getString(" delivery_address"),
                    rs.getString("delivery_status")
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
}}
    
