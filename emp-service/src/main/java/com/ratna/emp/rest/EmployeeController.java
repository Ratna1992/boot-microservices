package com.ratna.emp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.emp.model.Employee;
import com.ratna.emp.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") String id) {
		return employeeService.getEmployee(id);
	}
	
	@GetMapping
	public List<Employee> getEmplist(){
		return employeeService.getEmplist();
	}

}
