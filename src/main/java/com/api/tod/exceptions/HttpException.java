package com.api.tod.exceptions;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException{
	private HttpStatus status;

	public HttpException(HttpStatus status, String msg) {
		super(msg);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
