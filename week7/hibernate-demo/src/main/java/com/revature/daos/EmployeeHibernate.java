package com.revature.daos;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeHibernate implements EmployeeDao{

	/**
	 * Adds an employee to the database
	 * @return the employee with the new id or null if no record is created
	 */
	@Override
	public Employee add(Employee e) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(e);
		tx.commit();
		s.close();
		return e;
	}

	/**
	 * Retrieves an employee by its id from the database
	 * @return the employee if found or null otherwise
	 */
	@Override
	public Employee getById(int id) {
		Employee e = null;
		
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			e = s.get(Employee.class, id);

		}
		return e;
	}

	/**
	 * Retrieves an employee by its name from the database
	 * @return the employee if found or null otherwise
	 */
	@Override
	public Employee getByName(String name) {
		/*
		 * Use HQL
		 */
		Employee e = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			// define entity to be searched
			Root<Employee> root = cq.from(Employee.class);
			
			// defining "where name = ... && condition"
			Predicate predicateForName = cb.equal(root.get("name"), name);
//			Predicate predicateForSomethingElse = cb.equal(root.get("id"), passedInId);
//			Predicate predicateForNameAndSomethingElse = cb.and(predicateForName, predicateForSomethingElse);
			
			// cq.select(root).where(predicateForNameAndSomethingElse);
			cq.select(root).where(predicateForName);
			
			// retrieving the result
			e = (Employee) s.createQuery(cq).getSingleResult();
		}
		return e;
	}

	/**
	 * Retrieves all employees from the database
	 * @return a List of Employee objects
	 */
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			employees = s.createQuery("from Employee", Employee.class).list();
		}
		
//		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
//			employees = s.createNamedQuery("getAllNq", Employee.class).getResultList();
//		}
		
		return employees;
	}

	/**
	 * Retrieves employees by department id from the database
	 * @return a List of Employee objects
	 */
	@Override
	public List<Employee> getEmployeesByDepartmentId(int deptId) {
		List<Employee> employees = null;
		/*
		 * Use a Criteria
		 */
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			Predicate predicateForDept = cb.equal(root.get("department"), deptId);
			
			cq.select(root).where(predicateForDept);
			
			employees = (List<Employee>) s.createQuery(cq).getResultList();
		}
		return employees;
	}

	/**
	 * Updates an employee
	 */
	@Override
	public void update(Employee e) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.update(e);
			tx.commit();
		}
	}
	
	/**
	 * Deletes an employee
	 */
	@Override
	public void delete(Employee e) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(e);
			tx.commit();
		}
	}
}
