package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreater {
	
	JDBC_Connection con = new JDBC_Connection();	
	public void passQuery(String query) {
		PreparedStatement stm3;
		try {
			Connection conne = con.getConnection();
			stm3 = conne.prepareStatement(query);
			stm3.execute();
			stm3.close();
			conne.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Note Create DataBase in your localhost with Name College
	public static void database() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost/","root","root");

			String query = "create database quizproject";
			Statement stm = con.createStatement();
			stm.execute(query);
			System.out.println("quizproject Database Created Successfully");
			
			}catch(Exception e) {
				
				System.out.println("quizproject Database Allready Exist in DataBase");
			}
		
		
	}
	
	public static void createQuestionTable() {
		try {
		String query = "create table Questions(Question longtext,A varchar(255),B varchar(255),C varchar(255),D varchar(255),Ans varchar(255))";
		DataBaseCreater data = new DataBaseCreater();
		data.passQuery(query);
		
		System.out.println("Questions Table Created Successfully");
		}catch(Exception e) {
			
			System.out.println("Questions Table Allready Exist in DataBase");
		}
		
		
	}
	
	public static void createRegisterAdminTable() {
		try {
		String query = "create table registerAdmin(Sr_no int primary key auto_increment,Admin_Name varchar(255) unique,Password_  varchar(255))";
		DataBaseCreater data = new DataBaseCreater();
		data.passQuery(query);
		
		System.out.println("Admin Table Table Created Successfully");
		}catch(Exception e) {
			
			System.out.println("Admin Table Table Allready Exist in DataBase");
		}
		
		
		
		
	}
	
	
	public static void createStudentTable() {
		try {
		String query = "create table student(ID int primary key auto_increment,First_Name varchar(255),Last_Name varchar(255),score int,AttemptDate date,grade varchar(255),Mobile_No varchar(255) UNIQUE)";
		DataBaseCreater data = new DataBaseCreater();
		data.passQuery(query);
		
		System.out.println("Student Table Table Created Successfully");
		}catch(Exception e) {	
			System.out.println("Student Table Table Allready Exist in DataBase");
		}	
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		database();
		createQuestionTable();
		createRegisterAdminTable();
		createStudentTable();

	}



}
