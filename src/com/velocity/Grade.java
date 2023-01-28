package com.velocity;

public class Grade {
	
	public String checkGarade(int marks) {
		
		if (marks >=8) {
			return "A";
		}
		
		else if (marks >=6) {
			return "B";
		}
			
		else if (marks ==5) {
			return "C"; 
		}
		
		else return "D";
	}

}
