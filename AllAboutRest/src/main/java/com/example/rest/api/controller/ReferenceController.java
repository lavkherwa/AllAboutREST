package com.example.rest.api.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Resources")
public class ReferenceController {

	Collection<String> times = Arrays.asList("evening", "morning");

//	@GetMapping
//	public ResponseEntity<String> getAllResources() {
//		return ResponseEntity.ok("Requested all resources");
//	}

	@PostMapping
	public ResponseEntity<String> postResource(@RequestBody String value) {
		return ResponseEntity.ok("Resource create request for value: " + value);
	}

	/*- If you have different name for query parameter you can define as below
	 
		@GetMapping()
		public String getResource(@RequestParam(value = "name") String recourseName) {
			return "Requested Resource name is " + recourseName;
		}
	*/

	@GetMapping // localhost:8080/Resources?name=Lav&time=morning
	public ResponseEntity<String> getResourceByQuery(@RequestParam String name, //
			@RequestParam(required = false, defaultValue = "day") String time, //
			@RequestParam String secret) {

		if (!"123".equals(secret)) {
			/* How to pass any HTTP status */
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
		} else if (!"day".equals(time) && !times.contains(time)) {
			/* How to pass response with inbuilt methods and custom message */
			return ResponseEntity.badRequest().body("Time should be morning/evening");
		}

		return ResponseEntity.ok("Requested Resource name is " + name + " at time " + time);
	}

	/* Example of passing custom headers */
	@GetMapping("/CustomHeader") // localhost:8080/Resources
	public ResponseEntity<String> getResourceCustomHeader(@RequestHeader(value = "custom-header") String customHeader) {
		return ResponseEntity.ok("Custom header value is " + customHeader);
	}

	@GetMapping("/{id}") // localhost:8080/Resources/01
	public ResponseEntity<String> getResourceById(@PathVariable(value = "id") int id) {
		return ResponseEntity.ok("Requested Resource is " + id);
	}

	@PutMapping // localhost:8080/Resources/01
	public ResponseEntity<String> updateResource(@PathVariable(value = "id") int id) {
		return ResponseEntity.ok("Requested Resource is " + id);
	}

	@DeleteMapping // localhost:8080/Resources/01
	public ResponseEntity<String> deleteResource(@PathVariable(value = "id") int id) {
		return ResponseEntity.ok("Requested Resource is " + id);
	}

}
