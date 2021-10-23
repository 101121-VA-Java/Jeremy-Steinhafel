package com.revature.services;

import com.revature.models.BigAnimal;
import com.revature.models.SmallAnimal;

public class OopService {

	//Encapsulation deals with the separation of variables and methods within classes
	public void doEncapsulation() {
		BigAnimal bull = new BigAnimal();
		SmallAnimal bird = new SmallAnimal();
		
		bull.setNumberOfLegs(4);
		bird.setNumberOfLegs(2);
		
		System.out.println("Bulls have " + bull.getNumberOfLegs() + " legs");
		System.out.println("Birds have " + bird.getNumberOfLegs() + " legs");
	}
	
	//Inheritance is the passing down of characteristics from parent to child classes
	public void doInheritance() {
		BigAnimal ba = new BigAnimal();
		int foodAmount = ba.hungry();
		System.out.println("The amount of food for a hungry big animal "+ foodAmount);
	}
	
	//Polymorphism is the practice of method overloading and overriding
	public void doPolymorphism() {
		// overloading 
		BigAnimal ba = new BigAnimal();
		SmallAnimal sa = new SmallAnimal();
		sa.isItABird(2, false);
		sa.isItABird(2);
		
		//overriding
		ba.isItABird(4);
		ba.isItABird(2);
		
	}
	
	//Abstraction is the process of identifying only the required characteristics of an object
	public void doAbstraction() {
		BigAnimal ba = new BigAnimal();
		SmallAnimal sa = new SmallAnimal();
		
		String bigNoise = ba.whatDoesTheAnimalSay("rawr");
		String littleNoise = sa.whatDoesTheAnimalSay("TWEET");
		
		System.out.println(bigNoise);
		System.out.println(littleNoise);	
	}
	
	public void doException() {

		try {
			int a = 1/0;
			System.out.println(a);
		}
		catch (Exception e) {
			System.out.println("This is the problem:");
			e.printStackTrace();
		}
	}

}
