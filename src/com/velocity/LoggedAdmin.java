package com.velocity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoggedAdmin {
	Scanner sc=new Scanner(System.in);
	
	public void loggedIn() {
		JDBC_Connection con=new JDBC_Connection();
		Connection conn=con.getConnection();
		
		System.out.println("************Welcome Admin*************");
		System.out.println("\nSelect your option\n\n1.Display Result by Student ID\n\n2.Display Result of all students\n\n3.Modify Student Data\n\n4.Delete Record of Student by Id\n\n5.Insert Questions\n\n6.Logout.");
		String choice=sc.next();
		AdminAccessControl ctrl=new AdminAccessControl();
		switch(choice)
		{
			case "1":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					System.out.println("Enter Student Id");
					String id=sc.next();
			try {
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from student where id='"+id+"'");
				rs.next();
				
				System.out.println("");
				System.out.println("Result of Student.");
				System.out.println("---------------------------------------------------");
				System.out.println("Name :"+rs.getString(2)+"  "+ rs.getString(3)+"  "+"Score :"+rs.getInt(4)+"  "+"Grade :"+rs.getString(6));
				System.out.println("---------------------------------------------------");
				stmt.close();
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				loggedIn();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				System.err.println("Student Record not Found");
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				loggedIn();

			}
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					break;
					
			case "2":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("\nResult Of All students.");
				System.out.println("---------------------------------------------------");

				try {
					Statement stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student order by score");
					while(rs.next())
					{
					System.out.println("  Name :"+rs.getString(2)+" "+rs.getString(3)+"  Score :"+rs.getInt(4)+"  Grade :"+rs.getString(6));
					
					System.out.println("---------------------------------------------------");
					

					}
					stmt.close();
					System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					loggedIn();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			case "6":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("Successfully logout");
				FirstPage first=new FirstPage();
				first.firstPage();
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			case "4":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				ctrl.DeleteRecord();
				loggedIn();
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			case "5":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				ctrl.insertNewQuestions();
				loggedIn();
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			case "3":
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				ctrl.modifyStudentData();
				loggedIn();
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
				
			
			default:

				System.err.println("wrong input.....Please select right option");
				
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				loggedIn();
				break;
				
				
		}
		
	}

}
