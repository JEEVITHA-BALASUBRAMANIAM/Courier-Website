package com.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentsDao {
    private Connection con;

    public PaymentsDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vsbdb", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertPayment(Payment payment) {
        String sql = "INSERT INTO Payments (payment_date, amount, payment_method) VALUES (?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, payment.getPaymentDate());
            pst.setBigDecimal(2, payment.getAmount());
            pst.setString(3, payment.getPaymentMethod());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                String paymentDate = rs.getString("payment_date");
                BigDecimal amount = rs.getBigDecimal("amount");
                String paymentMethod = rs.getString("payment_method");
                Payment payment = new Payment(paymentDate, amount, paymentMethod);
                payment.setPaymentId(paymentId);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
