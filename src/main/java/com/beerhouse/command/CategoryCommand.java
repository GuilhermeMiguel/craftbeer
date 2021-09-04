package com.beerhouse.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryCommand {

	@NotNull
	@NotBlank
	private String name;
	
	private String description;
	
	private Boolean enabled = Boolean.TRUE;
}
