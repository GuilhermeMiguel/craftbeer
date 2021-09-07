package com.beerhouse.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
	
	private Long id;
	private String name;
	private Double milliliters;
	private String alcoholContent;
	private BigDecimal price;
	private CategoryDto category;
}
