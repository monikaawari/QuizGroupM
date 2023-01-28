package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class AdminLogin {
	
	Scanner sc=new Scanner(System.in);
	JDBC_Connection con = new JDBC_Connection();
	Connection conn = con.getConnection();
	
	public void registerAdmin() throws SQLException{
		System.out.println("Enter your Userid");
		String userId=sc.next();
		System.out.println("Enter your Passwod");
		String password=sc.next();
		PreparedStatement stmt=conn.prepareStatement("insert into registerAdmin values(null,?,?)");
		stmt.setString(1, userId);
		stmt.setString(2, password);
		stmt.executeUpdate();
		System.out.println("Registerd successfully");
		admin();
	}
	
	public boolean loginAdmin() throws SQLException{
		boolean flag=false;
		System.out.println("Enter your User id");
		String userId=sc.next();
		System.out.println("Enter your Password");
		String password=sc.next();
		PreparedStatement stmt=conn.prepareStatement("select * from registerAdmin where Admin_Name=?");
		stmt.setString(1, userId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{
		String Admin_name=rs.getString(2);
		String Admin_Password=rs.getString(3);
		if(userId.equals(Admin_name)&&password.equals(Admin_Password))
		{
			flag=true;
			
		}
		else
		{	flag=false;
			
		}
		
		}
		if(flag==true)
		{
			System.out.println("\nLogin Successful");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			LoggedAdmin log=new LoggedAdmin();
			log.loggedIn();
		}
		else
		{
			System.out.println("\nLogin UnSuccessful....Try again");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
		return flag;
		
	}
	
	public void admin() {
		System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("\n===========================  Welcome to the admin page ========================");
		System.out.println("\n\nSelect your option\n1.Register\n2.Login\n3.Go back");
		String choice=sc.next();
		AdminLogin admin=new AdminLogin();
		switch(choice)
		{
			case "1":
			try {
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				admin.registerAdmin();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			case "2":
			try {
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				admin.loginAdmin();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			case "3":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				FirstPage first=new FirstPage();
				first.firstPage();		
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				break;
				
				
			default:
				System.out.println("Wrong input");
				admin.admin();
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				break;
		}
	}
	

}
