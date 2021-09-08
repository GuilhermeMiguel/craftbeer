package com.beerhouse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beerhouse.command.CategoryCommand;
import com.beerhouse.dto.CategoryDto;
import com.beerhouse.entity.CategoryEntity;
import com.beerhouse.exception.CategoryNotFoundException;
import com.beerhouse.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public CategoryDto registerCategory(CategoryCommand newCategory) {
		var savedCategory = categoryRepository.save(toCategoryEntity(newCategory));
		return toCategoryDto(savedCategory);
	}
	
	public CategoryDto findCategoryById(Long id) {

		var foundCategory = findRegisterCategoryById(id);
					
		return toCategoryDto(foundCategory);
	}
	
	public List<CategoryDto> findAllCategories(Integer page, Integer size) {
		
		List<CategoryEntity> foundCategories = new ArrayList<>();
		
		if(page != null && size != null) 
			foundCategories = categoryRepository.findAll(PageRequest.of(page - 1, size)).getContent();
		else	
			foundCategories = categoryRepository.findAll();
		
		if(foundCategories == null || foundCategories.size() == 0)
			new CategoryNotFoundException("Categories not found");
				
			
		return foundCategories.stream().map(this::toCategoryDto).collect(Collectors.toList());
	}
	
	public CategoryDto updateCompleteCategoryById(Long id,  CategoryCommand category) {
		
		var categoryFound = findRegisterCategoryById(id);
				
		categoryFound.setName(category.getName());
		categoryFound.setDescription(category.getDescription());
		categoryFound.setEnabled(category.getEnabled());
				
					
		var updatedCategory = categoryRepository.save(categoryFound);
		
		return toCategoryDto(updatedCategory);
	}
	
	public CategoryDto updateCategoryById(Long id, Map<String, Object> changesInCategory) {
		var categoryFound = findRegisterCategoryById(id);
		
		changesInCategory.forEach(
	                (change, value) -> {
	                    switch (change){
	                        case "name": categoryFound.setName((String) value); break;
	                        case "description": categoryFound.setDescription((String) value); break;
	                        case "enabled":  categoryFound.setEnabled((Boolean) value); break;
	                    }
	                }
	        );
		 
		var updatedCategory = categoryRepository.save(categoryFound);
		
		return toCategoryDto(updatedCategory);
	}
	
	protected CategoryEntity findRegisterCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() ->	new CategoryNotFoundException("Category not found for id: " + id));
	}
		
	private CategoryEntity toCategoryEntity(CategoryCommand categoryCommand) {
		return modelMapper.map(categoryCommand, CategoryEntity.class);
	}
	
	private CategoryDto toCategoryDto(CategoryEntity categoryEntity) {
		return modelMapper.map(categoryEntity, CategoryDto.class);
	}
	

}
