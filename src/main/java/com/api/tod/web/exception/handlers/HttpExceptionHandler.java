package com.api.tod.web.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.tod.exceptions.HttpException;

@ControllerAdvice
public class HttpExceptionHandler implements BaseExceptionHandler<String, HttpException>{	
	@ExceptionHandler(HttpException.class)
	public ResponseEntity<String> handle(HttpException e) {
		return new ResponseEntity<String>(e.getMessage(), e.getStatus());
	}
	
}
