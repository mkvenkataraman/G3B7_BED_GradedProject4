package com.gl.empmgt.controller;

// Importing required classes and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.empmgt.entity.Role;
import com.gl.empmgt.service.RoleService;

/**
 * RestController annotation defines this class as a controller where every
 * method returns a domain object instead of a view. It is shorthand for
 * including both @Controller and @ResponseBody.
 */
@RestController
// RequestMapping annotation to map HTTP requests to handler methods of MVC and REST controllers.
@RequestMapping("/api/roles")
public class RoleController {

	// Autowired to wire the bean called roleService automatically.
	@Autowired
	private RoleService roleService;

	/**
	 * PostMapping annotation for mapping HTTP POST requests onto specific handler
	 * methods. Specifically, this method is used to create a new role.
	 *
	 * @param role The role object that will be created, which is expected to be
	 *             passed in the body of the request.
	 * @return A ResponseEntity containing the created role and HTTP status.
	 */
	@PostMapping("/")
	public ResponseEntity<Role> createRole(@RequestBody Role role) {
		// Calling the createRole method from roleService and storing the result in
		// createdRole.
		Role createdRole = roleService.createRole(role.getName());
		// Returning the createdRole with the status CREATED.
		return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
	}
}
