package com.gl.empmgt.service;

import com.gl.empmgt.entity.Role;

/**
 * Interface for role-related services. Defines the contract for role management
 * operations.
 */
public interface RoleService {

	/**
	 * Creates a new role with the given name.
	 *
	 * @param name The name of the role to create.
	 * @return The created Role object.
	 */
	Role createRole(String name);
}
