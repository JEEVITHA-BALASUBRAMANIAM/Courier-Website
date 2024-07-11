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

@WebServlet("/AddDeliveryDetailsCont")
public class AddDeliveryDetailsCont extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/vsbdb";
    String unm = "root";
    String pwd = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deliveryDate = request.getParameter("delivery_date");
        String deliveryAddress = request.getParameter("delivery_address");
        String deliveryStatus = request.getParameter("delivery_status");

        Connection con = null;
        String msg = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(url, unm, pwd);
            String sql = "INSERT INTO DeliveryDetails (delivery_date, delivery_address, delivery_status) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, deliveryDate);
            pst.setString(2, deliveryAddress);
            pst.setString(3, deliveryStatus);

            int res = pst.executeUpdate();
            if (res > 0) {
                msg = "Delivery details added successfully";
            }
            con.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        request.setAttribute("msg", msg);
        getServletContext().getRequestDispatcher("/DeliveryDetails.html").forward(request, response);
    }
}