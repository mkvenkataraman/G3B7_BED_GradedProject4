package com.gl.empmgt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort; // Necessary for sorting capabilities

import com.gl.empmgt.entity.Employee;

/**
 * Interface for employee-related services. Defines the contract for employee
 * management operations.
 */
public interface EmployeeService {

	/**
	 * Retrieve all employees without any sorting.
	 *
	 * @return A list of all employees.
	 */
	List<Employee> getAllEmployees();

	/**
	 * Retrieve all employees with sorting options.
	 *
	 * @param sort Sorting parameters.
	 * @return A sorted list of employees.
	 */
	List<Employee> getAllEmployees(Sort sort);

	/**
	 * Find employees by their first name.
	 *
	 * @param firstName The first name to search for.
	 * @return A list of employees matching the first name.
	 */
	List<Employee> findEmployeesByFirstName(String firstName);

	/**
	 * Add a new employee.
	 *
	 * @param employee Employee object to be added.
	 * @return The added employee.
	 */
	Employee addEmployee(Employee employee);

	/**
	 * Find an employee by ID.
	 *
	 * @param id The ID of the employee to find.
	 * @return An Optional containing the found employee or empty if not found.
	 */
	Optional<Employee> findEmployeeById(Long id);

	/**
	 * Update an employee.
	 *
	 * @param employeeDetails Employee object with updated details.
	 * @return The updated employee.
	 */
	Employee updateEmployee(Employee employeeDetails);

	/**
	 * Delete an employee by ID.
	 *
	 * @param id The ID of the employee to delete.
	 */
	void deleteEmployee(Long id);
}
