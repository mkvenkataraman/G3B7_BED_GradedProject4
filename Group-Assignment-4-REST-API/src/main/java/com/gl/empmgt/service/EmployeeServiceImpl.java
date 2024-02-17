package com.gl.empmgt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.gl.empmgt.entity.Employee;
import com.gl.empmgt.repository.EmployeeRepository;

/**
 * Service implementation for managing employees.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository; // Injects the employee repository

	@Override
	public List<Employee> getAllEmployees() {
		// Retrieve all employees without sorting
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getAllEmployees(Sort sort) {
		// Retrieve all employees with the specified sorting
		return employeeRepository.findAll(sort);
	}

	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Employee addEmployee(Employee employee) {
		// Save the provided employee entity to the database. Requires admin authority.
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findEmployeeById(Long id) {
		// Find an employee by their ID
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> findEmployeesByFirstName(String firstName) {
		// Find employees whose first name contains the specified string,
		// case-insensitively
		return employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
	}

	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Employee updateEmployee(Employee employeeDetails) {
		// Update the employee with the given details. Requires admin authority.
		Long id = employeeDetails.getId(); // Extract the ID from the provided employee details
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
		existingEmployee.setFirstName(employeeDetails.getFirstName());
		existingEmployee.setLastName(employeeDetails.getLastName());
		existingEmployee.setEmail(employeeDetails.getEmail());
		// Additional fields can be set here as needed

		return employeeRepository.save(existingEmployee);
	}

	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteEmployee(Long id) {
		// Delete the employee with the specified ID. Requires admin authority.
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
		employeeRepository.delete(employee);
	}
}
