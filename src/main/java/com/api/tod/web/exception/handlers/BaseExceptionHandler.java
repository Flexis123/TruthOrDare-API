package com.api.tod.web.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public interface BaseExceptionHandler<T, EX extends Throwable>{
	public ResponseEntity<T> handle(EX e);
}
