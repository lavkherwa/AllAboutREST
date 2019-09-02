package com.example.rest.api.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3020961532423439502L;

	public BadRequestException(String message) {
		super(message);
	}

}
