package com.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		public LoginDao() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vsbdb", "root", "root");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
	//	static int i=0;
		public void StoreData(Login m) {
			String sql="insert into Logindetails(username, password) values (?,?)";
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, m.getUsername());
				pst.setString(2, m.getPassword());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
			
			public ResultSet retriveLogin() {
			String sql = "select * from Logindetails";
			try {
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			}
}









