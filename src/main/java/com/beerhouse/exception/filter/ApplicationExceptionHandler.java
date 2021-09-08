package com.beerhouse.exception.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.beerhouse.dto.ExceptionResponseDto;
import com.beerhouse.exception.HttpResponseException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(HttpResponseException.class)
	public final ResponseEntity<ExceptionResponseDto> handleHttpExceptions(HttpResponseException ex, WebRequest request) {
		var response = handleAllExceptions(ex, request).getBody();

		HttpStatus httpStatus = ex.getHttpStatus();

		response.setHttpStatus(httpStatus.value());
		response.setTitle(ex.getTitle());
		

		return new ResponseEntity<ExceptionResponseDto>(response, httpStatus);
	}
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponseDto> handleAllExceptions(Exception ex, WebRequest request) {
		var response = new ExceptionResponseDto();

		response.setTimestamp(new Date().getTime());
		response.setMessage(ex.getMessage());
		response.setTitle("Internal Server Error");
		
		if (request != null && request instanceof ServletWebRequest) {
			HttpServletRequest httpRequest = ((ServletWebRequest) request).getRequest();

			response.setPath(httpRequest.getRequestURI());
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		ex.printStackTrace();

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
