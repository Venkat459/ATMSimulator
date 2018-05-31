package com.learning.excerise.atm.simulator;

import java.util.Scanner;

public class Summarize {
	  public  int sumOfNumbersFromSystemIn() {
		System.out.println("Enter firstNumber");
		
	    Scanner scanner = new Scanner(System.in);
	    int firstSummand = Integer.parseInt(scanner.nextLine());

	    // int firstSummand = scanner.nextInt();
	    System.out.println("Enter second number");
	    int secondSummand = Integer.parseInt(scanner.nextLine());

	    // int secondSummand = scanner.nextInt();
	    return firstSummand + secondSummand;
	  }
	}

