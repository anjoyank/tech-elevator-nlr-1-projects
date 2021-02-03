package com.techelevator;

import java.util.Scanner;


public class MakeChange {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the amount of the bill: ");
		String billInput = scanner.nextLine();
		double billTotal = Double.parseDouble(billInput);
		System.out.println("Please enter the amount tendered: ");
		String tenderInput = scanner.nextLine();
		double amountTendered = Double.parseDouble(tenderInput);
		double changeTendered = amountTendered - billTotal;
		System.out.println("The change required is " + changeTendered);

	}
	
}
