package com.revature.driver;

import java.util.Scanner;

import com.revature.services.OopService;

public class Driver {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// make calls to encapsulation, inheritance, polymorphism, abstraction	
		
		int value = sc.nextInt();
		OopService oop = new OopService();
		
		System.out.println("Please enter an option with a number 1-4");
		System.out.println("1: encapsulation");
		System.out.println("2: inheritance");
		System.out.println("3: polymorphism");
		System.out.println("4: abstraction");
		
		switch(value) {
			case 1:
				oop.doEncapsulation();
				break;
			case 2:
				oop.doInheritance();
				break;
			case 3:
				oop.doPolymorphism();
				break;
			case 4:
				oop.doAbstraction();
				break;
			default:
				System.out.println("Please select an option 1-4");
				break;
		
		}
		sc.close();
	}

}
