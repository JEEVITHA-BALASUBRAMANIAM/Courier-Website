package com.cont;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddOrderCont")
public class AddOrderCont extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/vsbdb";
    String username = "root";
    String password = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customer_id");
        String orderDate = request.getParameter("order_date");
        String totalAmount = request.getParameter("total_amount");
        String status = request.getParameter("status");

        Connection con = null;
        String msg = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO Orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(customerId));
            pst.setString(2, orderDate);
            pst.setBigDecimal(3, new java.math.BigDecimal(totalAmount));
            pst.setString(4, status);

            int res = pst.executeUpdate();
            if (res > 0) {
                msg = "Order data uploaded successfully";
            }
            con.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        request.setAttribute("msg", msg);
        getServletContext().getRequestDispatcher("/Order.html").forward(request, response);
    }
}