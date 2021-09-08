package com.beerhouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_category")
	private Long id;

	@NotNull
	@NotBlank
	@Size (max = 30)
	private String name;
	
	@NotNull
	@NotBlank
	@Size (max = 300)
	private String description;
		
	
	private Boolean enabled;
}
