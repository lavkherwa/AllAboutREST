package com.example.rest.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@GetMapping("/message")
	public ResponseEntity<String> getMessage() throws InterruptedException {

		return ResponseEntity.ok().body("Hello Everyone!!");
	}

}
