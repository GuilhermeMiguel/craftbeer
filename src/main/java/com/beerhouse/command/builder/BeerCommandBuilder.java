package com.beerhouse.command.builder;

import java.math.BigDecimal;

import com.beerhouse.command.BeerCommand;

public class BeerCommandBuilder {

	private String name;
	private Double milliliters;
	private String alcoholContent;
	private BigDecimal price;
	private Long idCategory;
	
	public BeerCommandBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public BeerCommandBuilder withMilliliters(Double milliliters) {
		this.milliliters = milliliters;
		return this;
	}
	
	public BeerCommandBuilder withAlcoholContent(String alcoholContent) {
		this.alcoholContent = alcoholContent;
		return this;
	}
	
	public BeerCommandBuilder withPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public BeerCommandBuilder withIdCategory(Long idCategory) {
		this.idCategory = idCategory;
		return this;
	}
	
	public BeerCommand build() {
		return new BeerCommand(name, milliliters, alcoholContent, price, idCategory);
	}
}
