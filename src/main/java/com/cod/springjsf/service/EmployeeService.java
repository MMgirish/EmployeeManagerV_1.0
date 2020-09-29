package com.cod.springjsf.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cod.springjsf.dao.IEmployeeDAO;
import com.cod.springjsf.model.Employee;


@Transactional (readOnly = true)
public class EmployeeService implements IEmployeeService{
	
	private IEmployeeDAO employeeDAO;
	
	//getters and setters
	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	
	//get employee
	public List<Employee> getAllEmployees() {
		return getEmployeeDAO().getAllEmployees();
	}

	//get employee by id
	public Employee getEmployeeByID(int id) {
		return getEmployeeDAO().getEmployeeById(id);
	}

	@Transactional (readOnly = false)
	public void addEmployee(Employee employee) {
		getEmployeeDAO().addEmployee(employee);
		
	}
	@Transactional (readOnly = false)
	public void updateEmployee(Employee employee) {
		getEmployeeDAO().updateEmployee(employee);
		
	}

	@Transactional(readOnly = false)
	public void deleteEmployee(Employee employee) {
		getEmployeeDAO().deleteEmployee(employee);
		
	}

}
