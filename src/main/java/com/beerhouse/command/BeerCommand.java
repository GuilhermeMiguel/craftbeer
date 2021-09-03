package com.beerhouse.command;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerCommand {
	
	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String ingredients;
	
	@NotNull
	@NotBlank
	private String alcoholContent;
	
	@NotNull
	@NotBlank
	private BigDecimal price;
	
	@NotNull
	@NotBlank
	private String category;
	  
}
