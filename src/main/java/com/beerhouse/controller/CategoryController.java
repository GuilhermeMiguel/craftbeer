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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.command.CategoryCommand;
import com.beerhouse.dto.CategoryDto;
import com.beerhouse.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Controller to Categories Management")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@Transactional
	@PostMapping()
	@ApiOperation(value = "Register a new category")
	public ResponseEntity<CategoryDto> registerCategory(@RequestBody CategoryCommand newCategory, HttpServletRequest request) {

		var categorySaved = categoryService.registerCategory(newCategory);

		return new ResponseEntity<>(categorySaved, HttpStatus.OK);
	}
	
	
	@GetMapping()
	@ApiOperation(value = "Search all categories with pagination")
	public ResponseEntity<List<CategoryDto>> findAllCategories(@RequestParam (required=false) Integer page, @RequestParam (required=false) Integer size) {

		var foundCategories = categoryService.findAllCategories(page, size);
			
		return new ResponseEntity<>(foundCategories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Search a category by Id")
	public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {

		var foundCategory = categoryService.findCategoryById(id);
			
		return new ResponseEntity<>(foundCategory, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update a complete category's register by Id")
	public ResponseEntity<CategoryDto> updateCompleteCategoryById(@PathVariable Long id, @RequestBody CategoryCommand category) {

		var updatedCategory = categoryService.updateCompleteCategoryById(id, category);
			
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value = "Update items of a category's registered by Id")
	public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable Long id, @RequestBody Map<String, Object> changesInBeer) {

		var updatedCategory = categoryService.updateCategoryById(id, changesInBeer);
			
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
}
