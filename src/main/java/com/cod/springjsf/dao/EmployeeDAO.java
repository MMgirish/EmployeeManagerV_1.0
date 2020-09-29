	package com.cod.springjsf.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cod.springjsf.model.Employee;


public class EmployeeDAO implements IEmployeeDAO{
	
	private SessionFactory sessionFactory;
	private static final Logger logger=LoggerFactory.getLogger(EmployeeDAO.class);
	
	
	//GETTERS AND SETTERS
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		List list=getSessionFactory().getCurrentSession().createQuery("from Employee").list();
		return list;
	}

	public void addEmployee(Employee employee) {
		getSessionFactory().getCurrentSession().save(employee);
		
	}

	public Employee getEmployeeById(int id) {
		logger.info("Inside get employee of EmployeeDAO.......................");
		List list=getSessionFactory().getCurrentSession().createQuery("from Employee where id=:id")
		.setParameter("id", id).list();
		logger.info("List value...................... "+list);
		logger.info("List.get(0)......................"+list.get(0));
		logger.info("(Employee) list.get(0).................. "+ (Employee)list.get(0));
		Employee theEmployee= (Employee) list.get(0);
		logger.info(" firstName:............................... "+theEmployee.getFirstName());
		return (Employee) list.get(0);
	}

	public void updateEmployee(Employee employee) {
		Query query= getSessionFactory().getCurrentSession().
				createQuery("update Employee set firstName=:firstName lastName=:lastName"
				+ "designation=:designation doj=:doj email=:email where id=:id");
		query.setParameter("firstName", employee.getFirstName());
		query.setParameter("lastName", employee.getLastName());
		query.setParameter("designation", employee.getDesignation());
		query.setParameter("doj", employee.getDoj());
		query.setParameter("email", employee.getEmail());
		query.setParameter("id", employee.getId());
		query.executeUpdate();
		logger.info("Update Successfull................");
	}

	public void deleteEmployee(Employee employee) {
		getSessionFactory().getCurrentSession().delete(employee);
		
	}
}
