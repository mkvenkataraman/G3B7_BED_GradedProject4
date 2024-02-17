package com.gl.empmgt;

// Import necessary Spring Boot annotations and classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class for the Spring Boot application. It is annotated
 * with @SpringBootApplication, which is a convenience annotation that adds all
 * of the following: - @Configuration: Tags the class as a source of bean
 * definitions for the application context. - @EnableAutoConfiguration: Tells
 * Spring Boot to start adding beans based on classpath settings, other beans,
 * and various property settings. - @ComponentScan: Tells Spring to look for
 * other components, configurations, and services in the com.gl.empmgt package,
 * allowing it to find controllers.
 */
@SpringBootApplication
public class GroupAssignment4RestApiApplication {

	/**
	 * The main method that serves as the entry point for the Spring Boot
	 * application.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Launch the Spring application context. This line initializes the application,
		// starting the auto-configuration,
		// classpath scanning, and setting up the application context with all the
		// components defined.
		SpringApplication.run(GroupAssignment4RestApiApplication.class, args);
	}
}
