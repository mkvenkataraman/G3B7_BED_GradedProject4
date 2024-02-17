package com.gl.empmgt.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.gl.empmgt.service.UserServiceImpl;

/**
 * Configuration class to setup Spring Security.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enables method level security
public class SecurityConfiguration {

	@Autowired
	private UserServiceImpl userServiceImpl; // Autowired to inject the UserServiceImpl

	/**
	 * Configures the HttpSecurity to define URL access rules, login, and logout
	 * behavior.
	 * 
	 * @param http HttpSecurity to configure
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**")
				.permitAll() // Allows access to these paths without authentication
				.anyRequest().authenticated() // Requires authentication for any other request
				.and().csrf().disable() // Disables CSRF protection for simplicity in this example
				.headers().frameOptions().disable() // Allows frames for H2 Console
				.and().formLogin().loginPage("/login").permitAll() // Custom login page
				.defaultSuccessUrl("/home", true) // Redirect to /home upon successful login
				.failureUrl("/login?error=true") // Redirect to /login with error on failure
				.and().logout().logoutSuccessHandler(customLogoutSuccessHandler()) // Custom logout handling
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().and().httpBasic(); // Enables basic
																										// authentication
		return http.build();
	}

	/**
	 * Custom logout success handler.
	 * 
	 * @return LogoutSuccessHandler
	 */
	@Bean
	public LogoutSuccessHandler customLogoutSuccessHandler() {
		return (HttpServletRequest request, HttpServletResponse response,
				org.springframework.security.core.Authentication authentication) -> {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().print("{\"message\":\"You have been logged out successfully.\"}");
			response.getWriter().flush();
		};
	}

	/**
	 * Configures global settings for AuthenticationManagerBuilder including
	 * in-memory and database authentication.
	 * 
	 * @param auth AuthenticationManagerBuilder to configure
	 * @throws Exception
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).authorities("ROLE_ADMIN");

		auth.userDetailsService(userServiceImpl).passwordEncoder(encoder); // Database authentication
	}

	/**
	 * Password encoder bean to be used for encoding passwords.
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
