package com.cont;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Product;
import com.model.ProductDao;

@WebServlet("/AddProductCont")
public class AddProductCont extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String productName = safeTrim(request.getParameter("productName"));
        String productDescription = safeTrim(request.getParameter("productDescription"));
        String priceStr = safeTrim(request.getParameter("price"));
        String weightStr = safeTrim(request.getParameter("weight"));
        String category = safeTrim(request.getParameter("category"));

        // Validate input fields
        ArrayList<String> errors = new ArrayList<>();

        if (productName.isEmpty()) {
            errors.add("Product Name is required!");
        }
        if (productDescription.isEmpty()) {
            errors.add("Product Description is required!");
        }
        if (priceStr.isEmpty()) {
            errors.add("Price is required!");
        } else {
            try {
                BigDecimal price = new BigDecimal(priceStr);
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    errors.add("Price must be greater than zero!");
                }
            } catch (NumberFormatException e) {
                errors.add("Invalid Price format!");
            }
        }
        if (!weightStr.isEmpty()) {
            try {
                BigDecimal weight = new BigDecimal(weightStr);
                if (weight.compareTo(BigDecimal.ZERO) < 0) {
                    errors.add("Weight cannot be negative!");
                }
            } catch (NumberFormatException e) {
                errors.add("Invalid Weight format!");
            }
        }

        // If there are validation errors, forward back to form with error messages
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            RequestDispatcher rd = request.getRequestDispatcher("AddProductForm.jsp"); // Adjust to your form JSP
            rd.forward(request, response);
            return;
        }

        // Create Product object
        BigDecimal price = new BigDecimal(priceStr);
        BigDecimal weight = weightStr.isEmpty() ? null : new BigDecimal(weightStr);
        Product product = new Product(productName, productDescription, price, weight, category);

        // Store product in database using ProductDao
        ProductDao productDao = new ProductDao();
        productDao.storeProduct(product);

        // Set attribute for success message
        request.setAttribute("productName", productName);

        // Forward to success page
        RequestDispatcher rd = request.getRequestDispatcher("Product.html");
        rd.forward(request, response);
    }

    // Utility method to handle potential null values from getParameter
    private String safeTrim(String param) {
        return param == null ? "" : param.trim();
    }
}