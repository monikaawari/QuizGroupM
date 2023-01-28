package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.velocity.*;



public class AdminAccessControl {
	Scanner sc=new Scanner(System.in);
	
	JDBC_Connection con=new JDBC_Connection();
	Connection conn=con.getConnection();
	
	public void DeleteRecord() {
		System.out.println("Enter Student id to delete record");
		int id=sc.nextInt();
		try {
			Statement stmt=conn.createStatement();
			int rowsAffected=stmt.executeUpdate("delete from student where id='"+id+"'");
			if(rowsAffected>0)
			{
			System.out.println("record deleted successfully");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}
			else
			{
				System.err.println("record not found");
			}
			Statement stmt1=conn.createStatement();
			int rowsAffected1=stmt1.executeUpdate("drop table t"+id);
			stmt1.close();
			
			
		} catch (SQLException e) {
			
//			e.printStackTrace();
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
	}
	
	public void insertNewQuestions() {
		System.out.println("You can enter new Question now");
		System.out.println("Enter Question");
		String question=sc.nextLine();
		System.out.println("enter first option");
		String opt1=sc.nextLine();
		System.out.println("enter second option");
		String opt2=sc.nextLine();
		System.out.println("enter third option");
		String opt3=sc.nextLine();
		System.out.println("enter fourth option");
		String opt4=sc.nextLine();
		System.out.println("enter the correct answer");
		String answer=sc.next();
		try {
			PreparedStatement stmt=conn.prepareStatement("insert into questions values(?,?,?,?,?,?)");
			stmt.setString(1,question);
			stmt.setString(2,opt1);
			stmt.setString(3,opt2);
			stmt.setString(4,opt3);
			stmt.setString(5,opt4);
			stmt.setString(6,answer);
			int rowsAffected=stmt.executeUpdate();
			if(rowsAffected>0)
			{
			System.out.println("question inserted successfully");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}
			else
			{
				System.err.println("question not inserted");
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}
		} catch (SQLException e) {
			System.err.println("question not inserted");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
		
	}
	
	public void modifyStudentData() {
		System.out.println("Enter Student id to modify record");
		int id=sc.nextInt();
		
//		checkId(id);
		System.out.println("select column name to modify ");
		
		System.out.println("\n1.First_name\n2.Last_Name\n3.Mobile No\n4.Score\n5.Grade\n6.Go Back");
		String modify=sc.next();
		switch(modify)
		{
		case "1":
			editData("First_name",id);
			break;
		case "2":
			editData("Last_Name",id);
			break;
		case "3":
			editData("Mobile_no",id);
			break;
		case "4":
			editData("Score",id);
			break;
		case "5":
			editData("Grade",id);
			break;
		case "6":
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			LoggedAdmin adm = new LoggedAdmin();
			adm.loggedIn();

			
			break;
		default:
				System.err.println("record not found");
				modifyStudentData();
				break;
		}
	}
	
	public void editData(String modify,int id) {
		System.out.println("Enter new value ");
		String newValue=sc.next();
		try {
			PreparedStatement  stmt=conn.prepareStatement("update student set "+modify+"=? where id=?");
																//from student where id='"+id+"'"
			stmt.setString(1, newValue);
			stmt.setInt(2, id);
			int rowsAffected=stmt.executeUpdate();
			if(rowsAffected>0)
			{
			System.out.println("record modified successfully");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}
			else
			{
				System.err.println("record not found");
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}
			
		} catch (SQLException e) {
			
			System.err.println("Record not found");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			
			
		}
	}
	
	public void checkId(int id) {
		try {
			PreparedStatement  stmt=conn.prepareStatement("select id from student where id=?");
																//from student where id='"+id+"'"
			
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				modifyStudentData();
			}
			System.err.println("record not found");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			LoggedAdmin log=new LoggedAdmin();
			log.loggedIn();
			
		} catch (SQLException e) {
			
			System.err.println("Record not found");
			System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			
		}
		
	}
	

}
