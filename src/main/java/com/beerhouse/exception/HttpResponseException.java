package com.beerhouse.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class HttpResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String title;
	private HttpStatus httpStatus;

	public HttpResponseException(String title, String message, HttpStatus httpStatus) {
		super(message);
		this.title = title;
		this.httpStatus = httpStatus;
	}
}
