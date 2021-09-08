package com.beerhouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.command.BeerCommand;
import com.beerhouse.dto.BeerDto;
import com.beerhouse.service.BeerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Controller to Bees Management")
@RequestMapping("/beer")
public class BeerController {

	@Autowired
	private BeerService beerService;
	
	
	@Transactional
	@PostMapping()
	@ApiOperation(value = "Register a new beer")
	public ResponseEntity<BeerDto> registerBeer(@RequestBody BeerCommand beer, HttpServletRequest request) {

		var savedBeer = beerService.registerBeer(beer);

		return new ResponseEntity<>(savedBeer, HttpStatus.OK);
	}
		
	@GetMapping()
	@ApiOperation(value = "Search all beers with pagination and filters")
	public ResponseEntity<List<BeerDto>> findAllBeers(@RequestParam (required=false) Integer page, @RequestParam (required=false) Integer size, 
			@RequestParam (required=false) String nameSearch, @RequestParam (required=false) Long idCategory) {

		var foundBeers = beerService.findAllBeerWithFilters(nameSearch, idCategory, page, size);
			
		return new ResponseEntity<>(foundBeers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Search a beer by Id")
	public ResponseEntity<BeerDto> findBeerById(@PathVariable Long id) {

		var foundBeer = beerService.findBeerById(id);
			
		return new ResponseEntity<>(foundBeer, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update a complete beer's register by Id")
	public ResponseEntity<BeerDto> updateCompleteBeerById(@PathVariable Long id, @RequestBody BeerCommand beer) {

		var updatedBeer =  beerService.updateCompleteBeerById(id, beer);
			
		return new ResponseEntity<>(updatedBeer, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value = "Update items of a beer's registered by Id")
	public ResponseEntity<BeerDto> updateBeerById(@PathVariable Long id, @RequestBody Map<String, Object> changesInBeer) {

		var updatedBeer = beerService.updateBeerById(id, changesInBeer);
			
		return new ResponseEntity<>(updatedBeer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a beer by Id")
	public ResponseEntity<Void> deleteBeerById(@PathVariable Long id) {

		beerService.deleteBeerById(id);
			
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
