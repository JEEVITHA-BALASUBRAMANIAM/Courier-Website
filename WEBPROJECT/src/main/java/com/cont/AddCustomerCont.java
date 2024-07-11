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

/**
 * Servlet implementation class AddCustomerCont
 */
@WebServlet("/AddCustomerCont")
public class AddCustomerCont extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/vsbdb";  // Change yourdatabase to your actual database name
    String unm = "root";
    String pwd = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Connection con = null;
        String msg = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(url, unm, pwd);
            String sql = "INSERT INTO Customers (first_name, last_name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, phone);
            pst.setString(5, address);

            int res = pst.executeUpdate();
            if (res > 0) {
                msg = "Customer data uploaded successfully";
            }
            con.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        request.setAttribute("msg", msg);
        getServletContext().getRequestDispatcher("/Customer.html").forward(request, response);
    }
}