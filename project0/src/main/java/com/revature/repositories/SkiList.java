package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Skis;

public class SkiList implements SkiDao{
	
	private List<Skis> skis;
	
	public SkiList() {
		skis = new ArrayList<>();
	}

	@Override
	public Skis add(Skis t) {
		skis.add(t);
		return t;
	}
	
	@Override
	public List<Skis> getAll(){
		return skis;
	}
	
	@Override
	public Skis remove(Skis t) {
		skis.remove(t);
		return t;
	}

	@Override
	public Skis getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Skis t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
