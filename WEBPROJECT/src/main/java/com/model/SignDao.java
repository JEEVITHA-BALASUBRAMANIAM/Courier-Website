package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignDao {

		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		public SignDao() {
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
		public void StoreData(Sign m) {
			String sql="insert into Signdetails(username, password) values (?,?)";
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, m.getUsername());
				pst.setString(2, m.getPassword());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
			
			public ResultSet retriveSign() {
			String sql = "select * from Signdetails";
			try {
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			}
}









