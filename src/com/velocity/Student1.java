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

		System.out.println("Please Enter Your Password");
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
		

			System.out.println("Please Enter Your First Name");
			String first_name  = scan.next();
			System.out.println("Please Enter Your Last Name");
			String last_name  = scan.next();
			System.out.println("Please Enter Your Phone Number");
			long phone  = scan.nextLong();
			
			try {
				PreparedStatement stm = conne.prepareStatement("insert into student values(null,?,?,null,null,null,?)");
				
				stm.setString(1, first_name);
				stm.setString(2, last_name);
				stm.setLong(3, phone);
				
				stm.addBatch();
				stm.executeBatch();
				stm.close();
				
				Statement stm1 = conne.createStatement();
				
				String query = "SELECT ID FROM STUDENT WHERE Mobile_No ='" + phone +"'";
				ResultSet res = stm1.executeQuery(query);
				res.next();

				int id = res.getInt(1);
				stm1.close();
				
				String str = String.valueOf(id);
				
				String tableQuery = "CREATE TABLE t" + str + "(score int, grade varchar(255), examDate date)";
				
				PreparedStatement stm3 = conne.prepareStatement(tableQuery);
				stm3.execute();
				
				return id;	
			} catch (SQLException e) {
				
				System.out.println("Student Not Registered yet Please Try again");
				student();
			}
			
			return 0;
			
		}
	
		
	public void student() {
		
		while(true) {
		System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n*********************Welcome to Student Section*********************\n\n");
		System.out.println("Please Select Below Option\n\n1.Student Registration \n2.Student Login\n3.Go back ");		
		String choice = scan.next();
		Student1 std = new Student1();
		
		switch(choice) {
		
		case "1":
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			//int id = std.registerStudent();
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\\nYou have successfully Registered. ");
//changed coz id not returning			//System.out.println("\n\nPlease Note Your ID :" +id+ " \n\n\nPassword is your Registered Mobile Number ");
			System.out.println("\n\nPlease Note Your ID :" +std.registerStudent()+ " \n\n\nPassword is your Registered Mobile Number ");
			System.out.println("You Can Login Now");
			break;
			
			
		case "2":
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			//student login
			System.out.println("Please Enter Your ID : ");
			String id1 = scan.next();
			if (std.loginStudent(id1)) {
				System.out.println("You Have loged In Successfully\n");
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				LogedStudent log = new  LogedStudent();
				log.logedStudent(id1);
				
				try {
					con.getConnection().close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			break;
		
		case "3":
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			FirstPage fpg = new FirstPage();
			fpg.firstPage();
			
			break;
			
		default :
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			System.err.println("Wrong input : ");
			
		}
		
		
		}
		
		
	}
	


}
