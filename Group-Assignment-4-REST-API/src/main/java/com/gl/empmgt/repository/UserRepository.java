package com.gl.empmgt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.empmgt.entity.User;

/**
 * Repository interface for the User entity that extends JpaRepository. This
 * interface provides CRUD operations and a method to find a user by username.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by their username.
	 *
	 * @param username The username of the user to find.
	 * @return An Optional containing the user if found, otherwise empty.
	 */
	Optional<User> findByUsername(String username);
}
