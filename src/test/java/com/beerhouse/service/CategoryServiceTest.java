package com.beerhouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.command.CategoryCommand;
import com.beerhouse.command.builder.CategoryCommandBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Tests for Category Service")
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
		
	@Test
	void findAllPaged_WhenSuccessful() {
		
		Integer page = 1, size = 5;
		
		var category = categoryService.findAllCategories(page, size);
		
		assertNotNull(category);
	}
	
	@Test
	void findAllNotPaged_WhenSuccessful() {
		Integer page = null, size = null;
		
		var category = categoryService.findAllCategories(page, size);
		
		assertNotNull(category);
	}
	
	@Test
	void registerCategory_WhenSuccessful() {
			
		var newCategory = createCategoryCommand();
		
		var savedCategory = categoryService.registerCategory(newCategory);
		
		assertNotNull(savedCategory.getId());
	}
	
	
	@Test
	void updateCompleteCategoryById_WhenSuccessful() {
			
		var category = createCategoryCommand();
		
		Long id = 1L;
		
		var updatedCategory = categoryService.updateCompleteCategoryById(id, category);
		
		assertEquals(category.getName(), updatedCategory.getName());
	}
	
	
	@Test
	void updateCategoryById_WhenSuccessful() {
				
		Long id = 1L;
		
		Map<String, Object> changesInCategory = new HashMap<String, Object>();
		
		String newDescription = "teste4444";
		
		changesInCategory.put("description", newDescription);
		
		var updatedCategory = categoryService.updateCategoryById(id, changesInCategory);
		
		assertEquals(newDescription, updatedCategory.getDescription());
	}
	
	
	private CategoryCommand createCategoryCommand() {
		return new CategoryCommandBuilder()
				.withName("teste")
				.withDescription("testetestete")
				.isEnabled(true)
				.build();
	}
	

}
