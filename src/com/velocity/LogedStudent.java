package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.velocity.*;

public class LogedStudent {
	
Scanner scan = new Scanner(System.in);
	
	
	
	public void logedStudent(String id) {
		
		JDBC_Connection con1 = new JDBC_Connection();
		Connection conne1 = con1.getConnection();
		
		try {
			Statement stm = conne1.createStatement();
			
			String query = "SELECT * FROM student where ID ='" +id +"'";
			ResultSet res = stm.executeQuery(query);
			res.next();
			System.out.println("Hello-----" + res.getString(2));
			
			try {
		
			if (res.getString(6).isEmpty()) {
			}
			
			
			else {
				System.out.println("\n\nPlease Select below option :");
				
				System.out.println("\n1.Give Test \n2.Previous Test Score\n3.Logout");
				
				
				LogedStudent log = new LogedStudent();
				switch(scan.next().toUpperCase()) {
				case "1":
					test(id);
					log.logedStudent(id);
					break;
					
				case "2":
					Statement stm4 = conne1.createStatement();
					
					ResultSet rset = stm4.executeQuery("SELECT * FROM t"+id);
					
					System.out.print("\n\nName : " + res.getString(2));
					
					System.out.println(" " + res.getString(3)+"\n");
					System.out.println("\n Previous Test Scores.");
					System.out.println("---------------------------------------------------");

					System.out.println("||--------------Marks Obtained----------Grade----------||");
					System.out.println("||-----------------------------------------------------||");

					while(rset.next()) {
						
						
					System.out.println("           " + rset.getString(1)+" 	    	 " + rset.getString(2));
					
					System.out.println("---------------------------------------------------");
					}
					System.out.println();
					stm4.close();
					
					log.logedStudent(id);
					
					
				case "3":
					System.out.println("You Have Successfully Loged Out ):");
					System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					FirstPage fp = new FirstPage();
					fp.firstPage();
					
					break;
					
				default:
					
					System.err.println("Wrong Input");
					System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					logedStudent(id);
					
					
					
				}
				
				
				
			}
			
			}catch(Exception e) {
				System.out.println("\n\nPlease Select below option :");
				
				System.out.println("\n1.Give Test \n2.Logout");
				
				
				
				
				switch(scan.next().toUpperCase()) {
					
				case "1":
					test(id);
					LogedStudent log = new LogedStudent();
					log.logedStudent(id);
					break;
					
				case "L":
					System.out.println("You Have Successfully Loged Out ):");
					FirstPage fp = new FirstPage();
					fp.firstPage();
					
					break;
					
				default:
					System.out.println("Something went wrong.....you enterd Wrong Input");
					logedStudent(id);
					
				}
				
				
			}
			stm.close();
			conne1.close();
			
			
		} catch (SQLException e) {
			
			System.out.println("Wrong Usier ID Password");
		}
		
		
		
		
	}
	
	
	 public static void test(String id) {
		 
//		 	String query2 = "UPDATE student SET score = '?', grade ='?' WHERE id = '23'";
		 	JDBC_Connection con = new JDBC_Connection();
		 	Connection conne = con.getConnection();
		 	try {
		 	Exam ex = new Exam();
			int score = ex.giveExam();
			
			System.out.println("\nYour Test Score is : " + score);
			
			Grade grd = new Grade();
			
			String grade = grd.checkGarade(score);
			
			if (grade.equals("D")) {
				
				System.err.println("\n*********SORRY you are Fail*********");
				System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				
			}else {
			System.out.println("you the Class : " + grade);
			}
			
			PreparedStatement stm2 = conne.prepareStatement("UPDATE student SET score = '"+score+"'"+", grade ='"+grade+"'"+" WHERE id = '"+id+"'");
//			stm2.setInt(1, score);
//			stm2.setString(2, grade);
			stm2.executeUpdate();
			stm2.close();
			
			Connection conne1 = con.getConnection();
			String str1 = "insert into t"+id+"(score,grade) values(?,?)";
			PreparedStatement stm3 =conne1.prepareStatement(str1);
			stm3.setInt(1, score);
			stm3.setString(2, grade);
			stm3.addBatch();
			stm3.executeBatch();
//			stm3.executeQuery(str1);
		
			stm3.close();
			
		 	}catch(Exception e) {
		 		
		 		
		 	}
		 
	 }



}
