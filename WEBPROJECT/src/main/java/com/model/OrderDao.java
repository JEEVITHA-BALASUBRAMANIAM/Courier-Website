package com.model;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public OrderDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vsbdb", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeData(Order order) {
        String sql = "INSERT INTO Orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, order.getCustomerId());
            pst.setString(2, order.getOrderDate());
            pst.setDouble(3, order.getTotalAmount());
            pst.setString(4, order.getStatus());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieveOrders() {
        String sql = "SELECT * FROM Orders";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);
            rs = pst.executeQuery();
            if (rs.next()) {
                order = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getString("order_date"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}