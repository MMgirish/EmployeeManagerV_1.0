package com.cod.springjsf.dao;

import java.util.List;

import com.cod.springjsf.model.Employee;


public interface IEmployeeDAO {

	public List<Employee> getAllEmployees();
	
	public void addEmployee(Employee employee);
	
	public Employee getEmployeeById(int id);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
}
