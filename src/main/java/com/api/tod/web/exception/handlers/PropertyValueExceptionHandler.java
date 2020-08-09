package com.api.tod.web.exception.handlers;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PropertyValueExceptionHandler 
		implements BaseExceptionHandler<String, PropertyValueException>{

	@Override
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<String> handle(PropertyValueException e) {
		String msg = e.getMessage();
		if(msg.contains("not-null")) {
			String fieldName = msg.substring(msg.lastIndexOf('.') + 1);
			return new ResponseEntity<>(fieldName + " cannot be empty", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
}
