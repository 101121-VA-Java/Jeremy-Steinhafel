package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.models.Customer;
import com.revature.models.Ski;
import com.revature.repositories.SkiDao;
import com.revature.repositories.SkiPostgres;
import com.revature.services.SkiServices;



@TestMethodOrder(OrderAnnotation.class)
public class SkisServicesTest {

	
	private static SkiServices ss = new SkiServices();
	private static SkiDao sd = new SkiPostgres();
	
	@Test
	public void addSkisCheck() {
		Ski newSkis = new Ski("Brand", "Model",  100.00, 3, 4);
		Ski actual = ss.addSkis(newSkis);
		Ski expected = sd.getByID(4) ;
		assertEquals(expected,actual);
	}
	
	@Test
	public void getSkisByModelCheck() {
		// Ski newSkis = new Ski("Brand", "Model",  100.00, 3, 4);
		Ski actual = ss.getSkisByModel("Model");
		Ski expected = sd.getByID(4) ;
		assertEquals(expected,actual);
	}
	
	@Test
	public void removeSkisCheck() {
		Ski newSkis = new Ski("Brand", "Model",  100.00, 1, 4);
		Ski actual = ss.removeSkis(newSkis);
		Ski expected = sd.getByID(4) ;
		assertEquals(expected,actual);
	}
	
	
}
