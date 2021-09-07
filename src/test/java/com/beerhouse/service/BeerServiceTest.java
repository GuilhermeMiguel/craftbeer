package com.beerhouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.command.BeerCommand;
import com.beerhouse.command.builder.BeerCommandBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Tests for Category Service")
class BeerServiceTest {

	@Autowired
	private BeerService beerService;
	
	@Test
	void findAllBeersPaged_WhenSuccessful() {
		
		Integer page = 1, size = 5;
		
		var beers = beerService.findAllBeers(page, size);
		
		assertNotNull(beers);
	}
	
	@Test
	void findAllBeersNotPaged_WhenSuccessful() {
		Integer page = null, size = null;
		
		var beers = beerService.findAllBeers(page, size);
		
		assertNotNull(beers);
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
		
		String newIngredients = "cevada,trigo,aveia";
		
		changesInBeer.put("ingredients", newIngredients);
		
		var updatedBeer = beerService.updateBeerById(id, changesInBeer);
		
		assertEquals(newIngredients, updatedBeer.getIngredients());
	}
	
	private BeerCommand createBeerCommand() {
		return new BeerCommandBuilder()
				.withName("cerveja 2")
				.withIngredients("ingredients")
				.withAlcoholContent("10")
				.withPrice(new BigDecimal("200"))
				.withIdCategory(1L)				
				.build();
	}

}
