package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ski;

public class SkiList implements SkiDao{
	
	private List<Ski> skis;
	
	public SkiList() {
		skis = new ArrayList<>();
	}

	@Override
	public Ski add(Ski t) {
		skis.add(t);
		return t;
	}
	
	@Override
	public List<Ski> getAll(){
		return skis;
	}
	
	@Override
	public Ski remove(Ski t) {
		skis.remove(t);
		return t;
	}

	@Override
	public Ski getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Ski t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Ski> getInStock() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
