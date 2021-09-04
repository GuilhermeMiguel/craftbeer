package com.beerhouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.command.CategoryCommand;
import com.beerhouse.dto.CategoryDto;
import com.beerhouse.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Controller to categories Management")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@Transactional
	@PostMapping()
	@ApiOperation(value = "")
	public ResponseEntity<Void> registerCategory(@RequestBody CategoryCommand newCategory, HttpServletRequest request) {

		categoryService.registerCategory(newCategory);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping()
	@ApiOperation(value = "")
	public ResponseEntity<List<CategoryDto>> findAllCategories() {

		var categories = categoryService.findAllCategories();
			
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "")
	public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {

		var category = categoryService.findCategoryById(id);
			
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "")
	public ResponseEntity<Void> updateCompleteCategoryById(@PathVariable Long id, @RequestBody CategoryCommand category) {

		categoryService.updateCompleteCategoryById(id, category);
			
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value = "")
	public ResponseEntity<Void> updateBeerById(@PathVariable Long id, @RequestBody Map<String, Object> changesInBeer) {

		categoryService.updateCategoryById(id, changesInBeer);
			
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
