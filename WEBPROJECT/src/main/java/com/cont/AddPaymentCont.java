package com.cont;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Payment;
import com.model.PaymentsDao;

@WebServlet("/AddPaymentCont")
public class AddPaymentCont extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String url = "jdbc:mysql://localhost:3306/vsbdb";
    String username = "root";
    String password = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paymentDate = request.getParameter("payment_date");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        String paymentMethod = request.getParameter("payment_method");

        Payment payment = new Payment(paymentDate, amount, paymentMethod);

        PaymentsDao dao = new PaymentsDao();
        boolean inserted = dao.insertPayment(payment);
        dao.closeConnection();

        if (inserted) {
            request.getRequestDispatcher("/Payment.html").forward(request, response);
        } else {
            request.setAttribute("errors", "Failed to add payment.");
            request.getRequestDispatcher("/AddPaymentForm").forward(request, response);
        }
    }
}
