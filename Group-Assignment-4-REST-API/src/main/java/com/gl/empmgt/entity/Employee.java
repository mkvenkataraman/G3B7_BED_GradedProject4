package com.gl.empmgt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Employee class represents the "employee" table in the "empmgt" schema of
 * the database. It is annotated with JPA annotations to define its relationship
 * with the database table.
 */
@Entity // This annotation specifies that the class is an entity and is mapped to a
		// database table.
@Table(name = "employee", schema = "empmgt") // Specifies the table name and schema in the database.
public class Employee {

	@Id // Marks the id field as the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way of increment of the specified
														// column(field).
	private Long id;

	@Column(name = "first_name") // Maps the firstName field to the column named "first_name" in the table.
	private String firstName;

	@Column(name = "last_name") // Maps the lastName field to the column named "last_name" in the table.
	private String lastName;

	// No column annotation here, the field name "email" is used as the default
	// column name.
	private String email;

	/**
	 * The default constructor is required by JPA.
	 */
	public Employee() {
	}

	/**
	 * A constructor with parameters to initialize the Employee entity.
	 *
	 * @param firstName The first name of the employee.
	 * @param lastName  The last name of the employee.
	 * @param email     The email address of the employee.
	 */
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// Standard getters and setters for the fields.
	// They allow accessing and modifying the private attributes of the entity.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
