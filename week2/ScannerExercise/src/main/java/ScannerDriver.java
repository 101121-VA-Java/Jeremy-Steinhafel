
import java.util.Scanner;

class ScannerDriver {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Create a menu that gives a user multiple options:
		 * 		- 1: get a random number
		 * 		- 2: Using StringBuffer, reverse a word of the user's choice
		 * 		- 3: exit the program
		 * This menu should repeat until the user decides to exit.
		 */

		
		ScannerDriver.menu();
		
		sc.close();
	}
	
	public static void menu(){
		System.out.println("Please choose an option from the menu:");
		System.out.println("1: get a random number");
		System.out.println("2: Using StringBuffer, reverse a word of the user's choice");
		System.out.println("3: exit the program");
		
		int choice = sc.nextInt();
		sc.nextLine();
		while(choice !=3) {
			switch (choice) {
				case 1: 
					double number = Math.round(Math.random()*1000);
					System.out.println("A random number: 1" + (int) number);
					break;
				case 2: 
					System.out.println("Please enter a word to reverse:");
					String a = sc.nextLine();
					StringBuffer sb = new StringBuffer(a);
					System.out.println(sb.reverse());
					break;
				case 3:
					break;
			default: 
			}
			System.out.println("Please choose another option from the menu:");
			choice = sc.nextInt();
		}
	}


}
