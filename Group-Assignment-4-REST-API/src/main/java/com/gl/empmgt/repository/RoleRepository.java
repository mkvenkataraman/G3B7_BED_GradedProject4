package com.gl.empmgt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.empmgt.entity.Role;

/**
 * Repository interface for Role entity that extends JpaRepository. Provides
 * CRUD operations and additional methods for Role entities.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Method to find a role by its name.
	 *
	 * @param name The name of the role to be found.
	 * @return An Optional containing the found role or an empty Optional if no role
	 *         is found.
	 */
	Optional<Role> findByName(String name);
}
