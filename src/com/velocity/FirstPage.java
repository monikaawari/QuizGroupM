package com.velocity;

import java.util.Scanner;

public class FirstPage {
	
	Scanner scan = new Scanner(System.in);
	
	public void firstPage() {
		while(true) {
		System.out.println("      \n\n=========================  WELCOME TO M-Quiz  =============================\n\n");
		
		
		System.out.println("Please Select below option : \n\n1.Student \n2.Admin\n3.Exit");
		
		
		
		switch(scan.next().toLowerCase()) {
		
		case "1":
			
			Student1 std = new Student1();
			std.student();
			
			
			break;
		case "2":
			AdminLogin admin=new AdminLogin();
		
			admin.admin();
			
			break;
		case "3":
			System.out.println("\n\n#####################################################################################");

			
			System.out.println("\n*******************************Thank you and Keep Learning*******************************");
			
			System.exit(0);
			
		default:
			
			System.out.println("OOps....Wrong Input ): ");
			System.out.println("\n\n#####################################################################################");

		
		
		}
		
		
	}
	}
	
	

	public static void main(String[] args) {
		
		FirstPage fr = new FirstPage();
		
		fr.firstPage();
		

	}

	

}
