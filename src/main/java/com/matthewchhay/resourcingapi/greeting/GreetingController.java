package com.matthewchhay.resourcingapi.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Annotations

@RestController
@RequestMapping("/greeting")  // localhost:8080/greeting
public class GreetingController {
	
	// get request 
	@GetMapping
	public String hello() {
		return "Hello, world!";
	}
	
	// How can we test a get request:
	// - using the browser
	// Postman
	// using the terminal: curl localhost:8080/greeting

	@GetMapping(value = "/goodbye") //localhost:8080/greeting/goodbye
	public String goodbye() {
		return "Goodbye, world";
	}
	
	// dynamic route
	// real life example: looking for a product by id
	
	
	// greet someone by their name
	
	// use curly braces for dynamic routes
	@GetMapping("/{name}")   // localhost:8080/greeting/martyna   localhost:8080/aidan
	public String greetSomeone(@PathVariable String name) {
		return String.format("Hello, %s", name);
		
	}
}
