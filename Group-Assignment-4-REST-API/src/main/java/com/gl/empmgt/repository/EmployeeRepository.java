package com.gl.empmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.empmgt.entity.Employee;

/**
 * Repository interface for Employee entity that extends JpaRepository. It
 * provides CRUD operations and finder methods for Employee entities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Method to find employees by a fragment of their first name, case-insensitive.
	List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

	// Method to retrieve all employees ordered by first name in ascending order.
	List<Employee> findAllByOrderByFirstNameAsc();

	// Method to retrieve all employees ordered by first name in descending order.
	List<Employee> findAllByOrderByFirstNameDesc();
}
