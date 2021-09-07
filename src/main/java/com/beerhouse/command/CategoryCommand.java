package com.beerhouse.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommand {

	@NotNull
	@NotBlank
	private String name;
	
	private String description;
	
	private Boolean enabled = Boolean.TRUE;
}
