package com.gl.empmgt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entity class representing the 'app_user' table in the 'empmgt' schema of the
 * database.
 */
@Entity
@Table(name = "app_user", schema = "empmgt")
public class User {

	@Id // Primary key of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented value for the primary key.
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	// Defining the many-to-many relationship between User and Role.
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "app_user_role", schema = "empmgt", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	// Default no-argument constructor required by JPA.
	public User() {
	}

	// Constructor with parameters to initialize User entity.
	public User(String username, String password, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	// Standard getter and setter methods for all the fields.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
