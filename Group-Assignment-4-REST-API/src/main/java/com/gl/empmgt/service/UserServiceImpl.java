package com.gl.empmgt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.empmgt.entity.Role;
import com.gl.empmgt.entity.User;
import com.gl.empmgt.repository.RoleRepository;
import com.gl.empmgt.repository.UserRepository;

/**
 * Service implementation for managing users.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User addUser(User user) {
		// Encode user password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Associate persisted roles with the user
		Set<Role> persistedRoles = user.getRoles().stream()
				.map(role -> roleRepository.findById(role.getId()).orElse(null)).collect(Collectors.toSet());
		user.setRoles(persistedRoles);
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User updateUser(Long id, User userDetails) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		// Update username and password
		existingUser.setUsername(userDetails.getUsername());
		existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		// Update roles
		Set<Role> updatedRoles = userDetails.getRoles().stream()
				.map(role -> roleRepository.findById(role.getId()).orElse(null)).collect(Collectors.toSet());
		existingUser.setRoles(updatedRoles);
		return userRepository.save(existingUser);
	}

	@Override
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		// Convert User roles to Spring Security GrantedAuthority
		Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}
}
