package com.employee.crud.service;

import com.employee.crud.entity.Employee;
import java.util.*;


public interface EmployeeService{
	Employee saveEmployee(Employee emp);
	List<Employee> fetchAllEmployees();
	Employee getEmployeeById(Long id);
	Employee updateEmployeeById(Long id,Employee emp);
	String deleteEmployeeById(Long id);
	List<String> getEmployeeNamesByDepartment(String dept);

}