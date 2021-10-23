package com.revature.models;

public class BigAnimal extends Animal {
	
	int weight = 80;

	public BigAnimal() {
		// TODO Auto-generated constructor stub
	}
	
	public String whatDoesTheAnimalSay(String sound) {
		sound = sound.toUpperCase();
		return sound;
	}
	
	public int hungry() {
		int foodAmount = this.food(weight);
		foodAmount += 1;
		return foodAmount;
	}
	
	@Override
	public void isItABird(int numberOfLegs) {
		System.out.println("Not a bird");
	}
	
	public void throwException() {
		System.out.println("That is not an animal");
	}

}
