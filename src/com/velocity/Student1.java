package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student1 {
	
	JDBC_Connection con = new JDBC_Connection();
	Connection conne = con.getConnection();
	
	
	Scanner scan = new Scanner(System.in);
	
	public boolean loginStudent(String id) {

		System.out.println("Please Enter Your Password: ");
		String pass = scan.next();
		
		try {
			Statement stm = conne.createStatement();
			
			String query = "SELECT Mobile_No FROM student where ID ='" +id +"'";
			ResultSet res = stm.executeQuery(query);
			res.next();
			boolean flag =(res.getString(1).equals(pass));
			stm.close();
			conne.close();
			con.getConnection().close();
			
			return flag ;
			
		} catch (SQLException e) {
			
			System.err.println("\nOhh....You entered Wrong Usier ID / Password");
		}
		
		return false;
	}
	
	
	public int registerStudent() {
	
		return 0;
		}
		
	public void student() {
		
	}
	


}
