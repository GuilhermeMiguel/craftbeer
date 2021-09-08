package com.beerhouse.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
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

import com.beerhouse.command.BeerCommand;
import com.beerhouse.command.CategoryCommand;
import com.beerhouse.command.builder.BeerCommandBuilder;
import com.beerhouse.command.builder.CategoryCommandBuilder;
import com.beerhouse.exception.BeerNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Tests for Beer Service")
@ActiveProfiles("test")
class BeerServiceTest {

	@Autowired
	private BeerService beerService;

	@Autowired
	private CategoryService categoryService;
	
	@BeforeEach
	public void registerCategoryAndBeer() {
		var newBeer = createBeerCommand();
		
		var newCategory = createCategoryCommand();
		
		var savedCategory = categoryService.registerCategory(newCategory);
		
		newBeer.setIdCategory(savedCategory.getId());
		
		beerService.registerBeer(newBeer);
		
	}
			
	
	@Test
	void findAllBeersPaged_WhenSuccessful() {
		
		Integer page = 1, size = 5;
				
		var beers = beerService.findAllBeerWithFilters(null, null, page, size);
		
		assertNotNull(beers);
		
		assertTrue(beers.size() > 0);
	}
	
	
	@Test
	void findAllBeerByName_WhenUnsuccessful() {
		
		String nameBeerThatDoesntExist = "DoesntExist";
		
		assertThrows(BeerNotFoundException.class, () -> beerService.findAllBeerWithFilters(nameBeerThatDoesntExist, null, null, null));
		
	}
	
	
	@Test
	void findAllBeersNotPagedAndWithoutFilters_WhenSuccessful() {
				
		var beers = beerService.findAllBeerWithFilters(null, null, null, null);
		
		assertNotNull(beers);
		
		assertTrue(beers.size() > 0);
	}
	
	@Test
	void updateCompleteBeerById_WhenSuccessful() {
			
		var beer = createBeerCommand();
		
		Long id = 1L;
		
		var updatedBeer = beerService.updateCompleteBeerById(id, beer);
		
		assertEquals(beer.getName(), updatedBeer.getName());
	}
	
	
	@Test
	void updateCategoryById_WhenSuccessful() {
				
		Long id = 1L;
		
		Map<String, Object> changesInBeer = new HashMap<String, Object>();
		
		Double newMilliliters = 370.0;
		
		changesInBeer.put("milliliters", newMilliliters);
		
		var updatedBeer = beerService.updateBeerById(id, changesInBeer);
		
		assertEquals(newMilliliters, updatedBeer.getMilliliters());
	}
	
	
	private BeerCommand createBeerCommand() {
		return new BeerCommandBuilder()
				.withName("Patricks Imperial Pils")
				.withMilliliters(350.0)
				.withAlcoholContent("10%")
				.withPrice(new BigDecimal("200"))
				.withIdCategory(1L)				
				.build();
	}
	
	private CategoryCommand createCategoryCommand() {
		return new CategoryCommandBuilder()
				.withName("Pilsen")
				.withDescription("Apresenta aroma e sabor acentuados pelo lúpulo, além da cor dourada.")
				.isEnabled(true)
				.build();
	}

}
