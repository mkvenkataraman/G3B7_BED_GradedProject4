package com.gl.empmgt.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.empmgt.entity.User;
import com.gl.empmgt.service.UserService;

/**
 * UserController manages the API endpoints for User-related operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	// Service that contains the business logic for user operations
	@Autowired
	private UserService userService;

	// Logger for the UserController
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Create a new user with the details provided in the request body.
	 *
	 * @param user User object that will be created.
	 * @return ResponseEntity with the created User object and HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	/**
	 * Retrieve a user by their unique identifier.
	 *
	 * @param id The unique identifier of the user to be retrieved.
	 * @return ResponseEntity with the User object if found, or not found status.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return userService.findUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Retrieve all users.
	 *
	 * @return ResponseEntity with a list of all User objects.
	 */
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return ResponseEntity.ok(users);
	}

	/**
	 * Update an existing user with the details provided in the request body.
	 *
	 * @param id   The unique identifier of the user to be updated.
	 * @param user User object containing the updated fields.
	 * @return ResponseEntity with the updated User object.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Delete a user by their unique identifier.
	 *
	 * @param id The unique identifier of the user to be deleted.
	 * @return ResponseEntity with a custom message indicating successful deletion
	 *         or error message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok("Deleted user id - " + id);
		} catch (Exception e) {
			logger.error("Failed to delete user with id {}: {}", id, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with id " + id);
		}
	}
}
