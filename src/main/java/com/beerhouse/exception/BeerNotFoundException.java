package com.beerhouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerNotFoundException extends HttpResponseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BeerNotFoundException(String message) {
		super("Beer(s) Not Found", message, HttpStatus.BAD_REQUEST);
	}

}
