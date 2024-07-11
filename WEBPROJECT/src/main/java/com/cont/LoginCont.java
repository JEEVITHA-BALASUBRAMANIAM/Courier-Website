package com.cont;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model.Login;
import com.model.LoginDao;

@WebServlet("/LoginCont")
public class LoginCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
		ArrayList<String> list=new ArrayList<String>();
		if(username.length()==0) {
			list.add("Enter username field!!!");
		}
		if(password.length()==0) {
			list.add("Enter the password!!!");
		}
	
		
		if(!list.isEmpty()) {
			request.setAttribute("errors", list);
			RequestDispatcher rd=request.getRequestDispatcher("LoginForm");
			rd.forward(request, response);
			return;
		}
		Login m=new Login(username, password);
		LoginDao md=new LoginDao();
		md.StoreData(m);
		request.setAttribute("tl", username);
		RequestDispatcher rd1=request.getRequestDispatcher("Login.html");
		rd1.forward(request, response);


	}

}