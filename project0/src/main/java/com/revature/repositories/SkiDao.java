package com.revature.repositories;

import java.util.List;

import com.revature.models.Ski;

public interface SkiDao extends GenericDao<Ski> {
	List<Ski> getInStock();
}
