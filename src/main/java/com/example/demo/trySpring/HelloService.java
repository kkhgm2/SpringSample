package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	@Autowired
	private HelloRepository repository;
	
	public Employee findOne(int id) {
		Map<String, Object> map = repository.findOne(id);
		int employeeId = (Integer) map.get("employee_id");
		String name = (String) map.get("employee_name");
		int age = (int) map.get("age");
		
		Employee emp = new Employee();
		emp.setEmployeeId(employeeId);
		emp.setEmployeeName(name);
		emp.setAge(age);
		return emp;
	}
}
