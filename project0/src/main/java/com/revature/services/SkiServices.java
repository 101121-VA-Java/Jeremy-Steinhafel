package com.revature.services;

import java.util.List;

import com.revature.repositories.SkiDao;
import com.revature.repositories.SkiList;
import com.revature.models.Skis;
import com.revature.exceptions.OutOfStockException;


public class SkiServices {

	private static SkiDao sd = new SkiList();
	
	public Skis addSkis(Skis s){
		Skis compareSkis = this.getSkisByModel(s.getModel());
		if(compareSkis != null ){ // && newSkis.getBrand().equals(s.getBrand())
			int total = compareSkis.getInStock() + s.getInStock();
			s.setInStock(total);
			compareSkis.setInStock(total);
			return compareSkis; 
		} else {
			return sd.add(s);	
		}
			
	}
	
	public Skis getSkisByModel(String model) {
		List<Skis> skis = sd.getAll();
		for(Skis s: skis) {
			if(s.getModel().equals(model)) {
				return s;
			}
		}
		return null;
	}
	
	public Skis removeSkis(Skis s) {
		Skis compareSkis = this.getSkisByModel(s.getModel());
		int total = compareSkis.getInStock() - s.getInStock();
		s.setInStock(total);
		s.setBrand(compareSkis.getBrand());
		s.setPrice(compareSkis.getPrice());
		s.setOfferStatus(compareSkis.getOfferStatus());
		compareSkis.setInStock(total);
		if(total == 0) { 
			sd.remove(s);
			return null;
		} else {
			return s;
		}
	}
	
	public void showInventory() {
		
	}
	
	public void moveToCart(int x) {
		
	}
	
}
