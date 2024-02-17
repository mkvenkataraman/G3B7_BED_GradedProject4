package com.gl.empmgt.controller;

// Importing the required Spring MVC annotation
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller handles navigation to the login and home pages.
 */
@Controller
public class LoginController {

	/**
	 * Handles the GET request for the login page. When the "/login" URL is
	 * accessed, it returns the name of the view that should be rendered, in this
	 * case, "login". The "login" view is expected to be a Thymeleaf template or a
	 * JSP file that resides in the resources/templates directory.
	 *
	 * @return The name of the login view template.
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Handles the GET request for the home page. When the "/home" URL is accessed,
	 * it returns the name of the view that should be rendered, in this case,
	 * "home". The "home" view is expected to be a Thymeleaf template or a JSP file
	 * that represents the home page of the application.
	 *
	 * @return The name of the home view template.
	 */
	@GetMapping("/home")
	public String home() {
		// The comment "home.html template" indicates that this method will look for a
		// "home.html" file in the resources/templates directory.
		return "home";
	}
}
