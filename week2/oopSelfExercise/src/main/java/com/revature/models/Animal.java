package com.revature.models;

public abstract class Animal {

	private String name;
	private String type;
	private int numberOfLegs;
	private boolean canFly;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
	public void setNumberOfLegs(int numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
	public boolean isCanFly() {
		return canFly;
	}
	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}
	
	public Animal() {
		
	}

	public Animal(String name, String type, int numberOfLegs, boolean canFly) {
		super();
		this.name = name;
		this.type = type;
		this.numberOfLegs = numberOfLegs;
		this.canFly = canFly;
	}
	
	public abstract String whatDoesTheAnimalSay(String sound);
		
	public int food(int weight) {
		if(weight >= 80) {
			return 5;
		} else {
			return 1;
		}
	}
	
	
	public void isItABird(int numberOfLegs) {
		if(numberOfLegs > 2) {
			System.out.println("Not a bird");
		}
		if(numberOfLegs <= 2) {
			System.out.println("Could be a bird");
		}
	}
	
	public void isItABird(int numberOfLegs, boolean canFly) {
		if(canFly && numberOfLegs > 2) {
			System.out.println("Sound like a mythical creature or fake flyer");
		}
		if(!canFly && numberOfLegs > 2) {
			System.out.println("Not a bird");
		}
		if(canFly && numberOfLegs <= 2) {
			System.out.println("Probably a bird, but could be a bat");
		}
		if(!canFly && numberOfLegs <= 2) {
			System.out.println("Could be a flightless bird");
		}
	}
	
	
	
	@Override
	public String toString() {
		return "Animal [name=" + name + ", type=" + type + ", numberOfLegs=" + numberOfLegs + ", canFly=" + canFly
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (canFly ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numberOfLegs;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (canFly != other.canFly)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfLegs != other.numberOfLegs)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
