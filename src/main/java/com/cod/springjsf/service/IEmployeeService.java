package com.cod.springjsf.service;

import java.util.List;

import com.cod.springjsf.model.Employee;


public interface IEmployeeService {

	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeByID(int id);
	
	public void addEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
}
