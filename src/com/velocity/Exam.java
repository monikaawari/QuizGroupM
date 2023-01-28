package com.velocity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exam {
	
public int giveExam() {
		
		Scanner scan = new Scanner(System.in);
		
		JDBC_Connection jdbc1 = new JDBC_Connection();
		
		Connection con = jdbc1.getConnection();
		int score = 0;
		
		Statement stm;
	
		return score;
	}
	
	
	
	


}
