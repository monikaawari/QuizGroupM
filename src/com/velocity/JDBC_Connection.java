package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connection {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/quizproject","root","root");
			
			return con;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}



