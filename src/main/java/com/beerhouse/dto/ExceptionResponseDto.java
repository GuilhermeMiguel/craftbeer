package com.beerhouse.dto;

import lombok.Data;

@Data
public class ExceptionResponseDto {

	private Long timestamp;
	private Integer httpStatus;
	private String title;
	private String message;
	private String path;
}
