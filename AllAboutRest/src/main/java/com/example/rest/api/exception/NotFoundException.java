package com.example.rest.api.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3020961532423439502L;

	public NotFoundException(String message) {
		super(message);
	}

}
