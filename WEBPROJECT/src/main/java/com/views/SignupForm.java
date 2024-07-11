package com.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SignupForm")

public class SignupForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		ArrayList<String> list=(ArrayList<String>) request.getAttribute("errors");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
		out.println("form { text-align: left; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background-color: #f9f9f9; }");
		out.println("h2 { text-align: center; margin-bottom: 20px; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		if (list != null) {
		    Iterator<String> itr = list.iterator();
		    out.println("<ul>");
		    while (itr.hasNext()) {
		        out.println("<li>" + itr.next() + "</li>");
		    }
		    out.println("</ul>");
		}
		out.println("<form action='SignupCont' method='post'>");
		out.println("<h2>Sign Up Details</h2>");
		out.println("Username <input type='text' name='username'><br/><br/>");
		out.println("Password <input type='password' name='password'><br/><br/>");
		out.println("<input type='submit' name='Submit'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	

	}

}