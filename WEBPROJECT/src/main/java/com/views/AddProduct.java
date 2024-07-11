package com.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Retrieve errors from request attribute
        ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");

        out.println("<html>");
       
        out.println("<style>");
		out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
		out.println("form { text-align: left; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background-color: #f9f9f9; }");
		out.println("h2 { text-align: center; margin-bottom: 20px; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
        out.println("<body>");
        

        // Display errors if there are any
        if (errors != null && !errors.isEmpty()) {
            out.println("<div style='color: red;'>");
            out.println("<h3>Errors:</h3>");
            out.println("<ul>");
            for (String error : errors) {
                out.println("<li>" + error + "</li>");
            }
            out.println("</ul>");
            out.println("</div>");
        }

        // Display the form
        out.println("<form action='AddProductCont' method='post'>");
        out.println("<h2>Product Details</h2>");
        out.println("Product Name:<br/> <input type='text' name='productName'><br/><br/>");
        out.println("Product Description:<br/> <textarea name='productDescription' rows='4' cols='50'></textarea><br/><br/>");
        out.println("Price:<br/> <input type='text' name='price'><br/><br/>");
        out.println("Weight:<br/> <input type='text' name='weight'><br/><br/>");
        out.println("Category:<br/> <input type='text' name='category'><br/><br/>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Handle form submission by invoking doGet
    }
}