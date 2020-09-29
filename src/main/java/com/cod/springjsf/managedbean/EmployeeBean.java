package com.cod.springjsf.managedbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.cod.springjsf.model.Employee;
import com.cod.springjsf.service.IEmployeeService;


@ManagedBean (name = "employeeBean")
@RequestScoped
public class EmployeeBean implements Serializable{

	private static final long serialVersionUID=1L;
	private static final String ERROR="error";
	//private static final String RELOAD="reload";
	//private static final String UPDATE="update";
	Logger logger=Logger.getLogger(getClass().getName());
	//private static final String UPDATED="updated";
	
	//private static final Logger logger=(Logger) LoggerFactory.getLogger(EmployeeManagedBean.class);
	
	
	@ManagedProperty(value = "#{EmployeeService}")
	private IEmployeeService employeeService;
	
	List<Employee> employees;
	
	private int id;
	private String firstName;
	private String lastName;
	private String designation;
	private Date doj;
	private String email;
	
	public EmployeeBean() {
		doj= new Date();	
	}
	
	public String addEmployee() {
		try {
			Employee emp= new Employee();
			emp.setFirstName(getFirstName());
			emp.setLastName(getLastName());
			emp.setDesignation(getDesignation());
			emp.setDoj(getDoj());
			emp.setEmail(getEmail());
			getEmployeeService().addEmployee(emp);
			return "dashboard?faces-redirect=true";
		}catch (DataAccessException d) {
			d.printStackTrace();
		}
		return ERROR;
	}
	
	public void reset() {
		this.setFirstName("");
		this.setLastName("");
		this.setDesignation("");
		this.setDoj(null);
		this.setEmail("");
	}
	
	public List<Employee> getAllEmployees(){
		employees= new ArrayList<Employee>();
		employees.addAll(getEmployeeService().getAllEmployees());
		return employees;
	}
	
	//Delete employee method
	public String deleteEmployee(Employee employee) {
		getEmployeeService().deleteEmployee(employee);
		return "dashboard.xhtml?faces-redirect=true";
	}
	
	//get employee by id
	public String getEmployeeById(int id) {
		//logger.info("Inside get employee by id.... Now calling get employee by id");
		
		//logger.log(Level.FINEST ,"theEmployee value is:...................................."+theEmployee);
		try {
			Employee theEmployee=getEmployeeService().getEmployeeByID(id);
			ExternalContext externalContext= FacesContext.getCurrentInstance().getExternalContext();
			Map<String,Object> requestMap= externalContext.getRequestMap();
			requestMap.put("employeeBean", theEmployee);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//logger.info("displaying the map................"+requestMap);
		return "update";
	}
	
	//update Employee
	public String updateEmployee(EmployeeBean employee) {
		Employee emp= new Employee();
		emp.setFirstName(getFirstName());
		emp.setLastName(getLastName());
		emp.setDesignation(getDesignation());
		emp.setDoj(getDoj());
		emp.setEmail(getEmail());
		getEmployeeService().updateEmployee(emp);
		return "dashboard?redirect-faces=true";
	}
	
	//getters and setters

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public List<Employee> getEmployees() {
		return getAllEmployees();
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	//navigation method
	public String processNavigation() {
		return "add-employee";
	}
	
	//add-success message
	public void saveSuccess() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "Employee Saved Successfully!"));
	}

	//getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
