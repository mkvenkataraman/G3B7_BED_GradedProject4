package com.gl.empmgt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Role class represents the "role" table in the "empmgt" schema of the
 * database. It is used to store role information for users.
 */
@Entity // Indicates that this class is an entity to be managed by JPA.
@Table(name = "role", schema = "empmgt") // Specifies the table name and schema for this entity.
public class Role {

	@Id // Marks this field as the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID.
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "roles") // Configures a many-to-many relationship with the User entity.
	private Set<User> users = new HashSet<>(); // Initializes the Set with an empty HashSet.

	// Standard constructors, getters, and setters.

	/**
	 * Default constructor for JPA.
	 */
	public Role() {
		super(); // Explicit call to the superclass constructor (Object class).
	}

	/**
	 * Parameterized constructor for creating a role with a name.
	 *
	 * @param name The name of the role.
	 */
	public Role(String name) {
		super(); // Explicit call to the superclass constructor (Object class).
		this.name = name;
	}

	// Getters and setters for the id and name fields.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// The users field is not exposed through getters and setters by design to
	// encapsulate the relationship handling.
}
