package com.revature;

import com.revature.exceptions.*;

public class Calculator {

	/*
	 * Should be able to
	 * 	- add
	 * 	- subtract
	 * 	- multiply
	 * 	- divide
	 * 		- throw Calculator Exception (Runtime) when attempting to divide by 0
	 *	- isPrime: checks if a number is Prime
	 */
	
	public double add(double x, double y) {
		return x + y;
	}
	
	public double subtract(double x, double y) {
		return x - y;
	}
	
	public double multiply(double x, double y) {
		return x * y;
	}
	
	public double divide(double x, double y) throws Exception {
		if(y == 0) {
			throw new CalculatorException(); 
		}
		return x / y;
	}
	
	public boolean isPrime(int i) {
		if(i%2 == 0 || i%3 == 0 && i != 2 && i != 3) {
			return false;
		}else {
			return true;
		}
	}
}
