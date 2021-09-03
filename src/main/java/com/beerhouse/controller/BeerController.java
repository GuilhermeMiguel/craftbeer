package com.beerhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.command.BeerCommand;
import com.beerhouse.dto.BeerDto;
import com.beerhouse.service.BeerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Controller to Beer Inventory Management")
@RequestMapping("/beer")
public class BeerController {

	@Autowired
	private BeerService beerService;
	
	
	@Transactional
	@PostMapping()
	@ApiOperation(value = "")
	public ResponseEntity<Void> registerBeer(@RequestBody BeerCommand beer, HttpServletRequest request) {

		beerService.registerBeer(beer);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping()
	@ApiOperation(value = "")
	public ResponseEntity<List<BeerDto>> findAllBeers() {

		var beer = beerService.findAllBeers();
			
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "")
	public ResponseEntity<BeerDto> findBeerById(@PathVariable Long id) {

		var beer = beerService.findBeerById(id);
			
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
}
