package com.example.rest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorMessage exceptionMessage = new ErrorMessage(ex.getLocalizedMessage(), "something went wrong!");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorMessage> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ErrorMessage exceptionMessage = new ErrorMessage(ex.getLocalizedMessage(), "Request is malformed!");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, HttpStatus.BAD_REQUEST);
	}

}
