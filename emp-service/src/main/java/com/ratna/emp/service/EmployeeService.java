package com.ratna.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.ratna.emp.model.Employee;

@Service
public class EmployeeService {

	public List<Employee> getEmplist() {

		List<Employee> empList = new ArrayList<>();

		for (int i = 0; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setId(i + "");
			employee.setAge((int) (Math.random() * 100) + "");
			employee.setMobile(849989912 + i + "");
			employee.setSal(123456 + i + "");
			employee.setName("emp" + i);
			empList.add(employee);
		}

		return empList;

	}

	public Employee getEmployee(String id) {

		Optional<Employee> findFirst = getEmplist().stream().filter(e -> e.getId().equalsIgnoreCase(id)).findFirst();
		if (findFirst.isPresent()) {
			return findFirst.get();
		} else {
			throw new ResourceAccessException("no employee with id " + id);
		}
	}

}
