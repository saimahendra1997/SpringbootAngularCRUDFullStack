package com.springbootbackend.fullstack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootbackend.fullstack.exception.ResourceNotFoundException;
import com.springbootbackend.fullstack.model.Employee;
import com.springbootbackend.fullstack.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
		@Autowired
		public EmployeeRepository employeeRepository;
		
		
		// Get All Employees
		@GetMapping("/employees")
		@CrossOrigin(origins = "http://localhost:4200")
		public List<Employee> getAllEmployees()
		{
			return employeeRepository.findAll();
		}

		// Create Employee Rest API
		@PostMapping("/employees")
		@CrossOrigin(origins = "http://localhost:4200")
		public Employee createEMployee(@RequestBody Employee employee)
		{
			// POST request contains JSON body
			return employeeRepository.save(employee);
		}
		
		// Get Employee By ID Rest API
		@GetMapping("/employees/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable long id)
		{
			// We are going to store id value into java variable for that we used @Path Annotation to map 
			// the above id to the Path variable
			Employee employee= employeeRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Employee Not exist With Id"+ id));
			// findById() returns Optional as return type
			// For that we use orElseThrow, If record not found throwing exception for that we need to pass
			// lamda expression to use functional interface 
			// We are returning the HTTP status for that we are using ResponseEntity class which is generic type 
			// for that passing Employee as generic type
			return ResponseEntity.ok(employee);
		}
		
		
		// Update Employee Rest API
		@PutMapping("/employees/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails)
		{
			Employee employee= employeeRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Employee Not exist With Id"+ id));
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmailId(employeeDetails.getEmailId());
			
			Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);

		}
		
		
		@DeleteMapping("/employees/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		    Employee employee = employeeRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Employee Not exist With Id " + id));
		    employeeRepository.delete(employee);
		    Map<String, Boolean> response = new HashMap<>();
		    response.put("deleted", Boolean.TRUE);
		    return ResponseEntity.ok(response);
		}
		
	
		
}
