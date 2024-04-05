package com.employee.crud.service;

import com.employee.crud.entity.Employee;
import com.employee.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public Employee saveEmployee(Employee emp) {
		return empRepository.save(emp);
	}

	@Override
	public List<Employee> fetchAllEmployees(){
		List<Employee> allEmp = empRepository.findAll();
		return allEmp;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> emp = empRepository.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}else {
			return null;
		}
	}


	@Override
	public List<String> getEmployeeNamesByDepartment(String dept) {
		List<Employee> employees = empRepository.findByDept(dept);
		return employees.stream().map(Employee::getName).collect(Collectors.toList());
	}
	@Override
	public Employee updateEmployeeById(Long id, Employee updatedEmp) { // Using a more descriptive parameter name
		Optional<Employee> existingEmpOptional = empRepository.findById(id);
		if (existingEmpOptional.isPresent()) {
			Employee originalEmp = existingEmpOptional.get();

			// Update fields only if values are provided in the update request
			if (updatedEmp.getName() != null && !updatedEmp.getName().isEmpty()) {
				originalEmp.setName(updatedEmp.getName());
			}
			if (updatedEmp.getDoj() != null && !updatedEmp.getDoj().isEmpty()) {
				originalEmp.setDoj(updatedEmp.getDoj());
			}
			if (updatedEmp.getStatus() != -1) {
				originalEmp.setStatus(updatedEmp.getStatus());
			}
			if (updatedEmp.getSalary() != -1) {
				originalEmp.setSalary(updatedEmp.getSalary());
			}
			if (updatedEmp.getDept() != null) {
				originalEmp.setDept(updatedEmp.getDept());
			}

			return empRepository.save(originalEmp);
		}
		return null;
	}

	@Override
	public String deleteEmployeeById(Long id) {
		if(empRepository.findById(id).isPresent()) {
			empRepository.deleteById(id);
			return "Employee Deleted Successfully";
		}
		return "Employee by this id doesn't Exist !";
	}

}
