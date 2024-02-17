package com.gl.empmgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.gl.empmgt.entity.Role;
import com.gl.empmgt.repository.RoleRepository;

/**
 * Service implementation for managing roles.
 */
@Service
public class RoleServiceImpl implements RoleService {

	// RoleRepository instance for database operations.
	private final RoleRepository roleRepository;

	/**
	 * Constructor for RoleServiceImpl with dependency injection for RoleRepository.
	 *
	 * @param roleRepository The repository used for role operations.
	 */
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	/**
	 * Create and save a new role with the given name.
	 *
	 * @param name The name of the role to be created.
	 * @return The created and saved Role entity.
	 */
	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Role createRole(String name) {
		Role role = new Role(name); // Create a new Role object.
		return roleRepository.save(role); // Save the role to the database and return it.
	}
}
