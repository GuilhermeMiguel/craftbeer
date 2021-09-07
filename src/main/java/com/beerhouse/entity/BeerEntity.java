package com.beerhouse.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "beer")
@Data
public class BeerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_beer")
	private Long id;

	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String ingredients;
	
	@NotNull
	@NotBlank
	@Column(name = "alcohol_content")
	private String alcoholContent;
	
	@NotNull
	private BigDecimal price;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private CategoryEntity category;
	
	@Deprecated
	public BeerEntity() {
	}

	public BeerEntity(String name, String ingredients, String alcoholContent, BigDecimal price, CategoryEntity category) {
		this.name = name;
		this.ingredients = ingredients;
		this.alcoholContent = alcoholContent;
		this.price = price;
		this.category = category;
	}

	
}
