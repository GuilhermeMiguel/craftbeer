package com.beerhouse.command.builder;

import com.beerhouse.command.CategoryCommand;

public class CategoryCommandBuilder {
	
	private String name;	
	private String description;	
	private Boolean enabled = Boolean.TRUE;
	
	public CategoryCommandBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public CategoryCommandBuilder withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public CategoryCommandBuilder isEnabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public CategoryCommand build() {
		return new CategoryCommand(name, description, enabled);
	}
}
