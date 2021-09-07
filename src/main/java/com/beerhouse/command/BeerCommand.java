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
	private Double milliliters;
	
	@NotNull
	@NotBlank
	private String alcoholContent;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	@NotBlank
	private Long idCategory;
	  
}
