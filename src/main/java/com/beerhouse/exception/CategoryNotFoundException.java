package com.beerhouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotFoundException extends HttpResponseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoryNotFoundException(String message) {
		super("Category(ies) Not Found", message, HttpStatus.BAD_REQUEST);
	}

}
