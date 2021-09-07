package com.beerhouse.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.command.CategoryCommand;
import com.beerhouse.command.builder.CategoryCommandBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Tests for Category Service")
@ActiveProfiles("test")
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	@BeforeEach
	public void registerCategory() {
		
		var newCategory = createCategoryCommand();
		
		categoryService.registerCategory(newCategory);
	}
	
		
	@Test
	void findAllPaged_WhenSuccessful() {
		
		Integer page = 1, size = 5;
		
		var categories = categoryService.findAllCategories(page, size);
		
		assertNotNull(categories);
		
		assertTrue(categories.size() > 0);
	}
	
	@Test
	void findAllNotPaged_WhenSuccessful() {
		Integer page = null, size = null;
		
		var categories = categoryService.findAllCategories(page, size);
		
		assertNotNull(categories);
		
		assertTrue(categories.size() > 0);
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
		
		String newDescription = "Apresenta aroma torrado e sabor amargo, que lembra café ou chocolate. Sua espuma é cremosa e pode ser clara ou amarronzada. É mais forte e escura que a Porter.";
		
		changesInCategory.put("description", newDescription);
		
		var updatedCategory = categoryService.updateCategoryById(id, changesInCategory);
		
		assertEquals(newDescription, updatedCategory.getDescription());
	}
	
	
	private CategoryCommand createCategoryCommand() {
		return new CategoryCommandBuilder()
				.withName("Pilsen")
				.withDescription("Apresenta aroma e sabor acentuados pelo lúpulo, além da cor dourada.")
				.isEnabled(true)
				.build();
	}
	

}
