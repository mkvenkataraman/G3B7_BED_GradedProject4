package com.gl.empmgt.controller;

// Necessary imports
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.empmgt.entity.Employee;
import com.gl.empmgt.service.EmployeeService;

// Controller annotation specifying the REST API base path for employees
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	// Auto-wiring the EmployeeService to interact with employee data
	@Autowired
	private EmployeeService employeeService;

	// Logger to output information to the console
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	// Method to get all employees with optional sorting parameter
	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String sort) {
		List<Employee> employees;
		if (sort != null && sort.equalsIgnoreCase("desc")) {
			// If 'desc' is specified, sort in descending order by firstName
			employees = employeeService.getAllEmployees(Sort.by("firstName").descending());
		} else {
			// Default sorting is ascending order by firstName
			employees = employeeService.getAllEmployees(Sort.by("firstName").ascending());
		}
		// Return the list of employees with an OK status
		return ResponseEntity.ok(employees);
	}

	// Method to get employees by their first name
	@GetMapping("/by-first-name/{firstName}")
	public ResponseEntity<List<Employee>> getEmployeesByFirstName(@PathVariable String firstName) {
		List<Employee> employees = employeeService.findEmployeesByFirstName(firstName);
		if (employees.isEmpty()) {
			// If no employees are found, return a Not Found status
			return ResponseEntity.notFound().build();
		}
		// Return the list of employees with an OK status
		return ResponseEntity.ok(employees);
	}

	// Method to create a new employee
	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		Employee createdEmployee = employeeService.addEmployee(employee);
		// Return the created employee with a Created status
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

	// Method to get a single employee by their ID
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		// Attempt to find the employee and return it, or return Not Found if it doesn't
		// exist
		return employeeService.findEmployeeById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Method to update an employee
	@PutMapping("/")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employeeDetails) {
		// Check if the employee details contain an ID
		if (employeeDetails.getId() == null) {
			// If no ID is present, return a Bad Request status
			return ResponseEntity.badRequest().body(null);
		}
		// Update the employee and return the updated instance
		Employee updatedEmployee = employeeService.updateEmployee(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}

	// Method to delete an employee by their ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		try {
			// Attempt to delete the employee
			employeeService.deleteEmployee(id);
			// If successful, return a message indicating deletion
			return ResponseEntity.ok("Deleted user id - " + id);
		} catch (Exception e) {
			// If an exception occurs, log it and return an Internal Server Error status
			logger.error("Failed to delete Employee with id {}: {}", id, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with id " + id);
		}
	}
}
