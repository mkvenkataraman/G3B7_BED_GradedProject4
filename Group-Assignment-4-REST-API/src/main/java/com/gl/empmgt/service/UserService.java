package com.gl.empmgt.service;

import java.util.List;
import java.util.Optional;

import com.gl.empmgt.entity.User;

/**
 * Interface for user-related services. Defines the operations for managing User
 * entities.
 */
public interface UserService {

	/**
	 * Add a new user to the system.
	 *
	 * @param user The user entity to be added.
	 * @return The added user entity.
	 */
	User addUser(User user);

	/**
	 * Find a user by their unique identifier.
	 *
	 * @param id The ID of the user to find.
	 * @return An Optional containing the found user or empty if no user is found.
	 */
	Optional<User> findUserById(Long id);

	/**
	 * Retrieve all users in the system.
	 *
	 * @return A list of all user entities.
	 */
	List<User> findAllUsers();

	/**
	 * Update the details of an existing user.
	 *
	 * @param id          The ID of the user to update.
	 * @param userDetails The new details for the user.
	 * @return The updated user entity.
	 */
	User updateUser(Long id, User userDetails);

	/**
	 * Delete a user by their unique identifier.
	 *
	 * @param id The ID of the user to delete.
	 */
	void deleteUser(Long id);
}
