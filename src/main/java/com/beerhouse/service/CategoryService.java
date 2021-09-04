package com.beerhouse.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void registerCategory(CategoryCommand newCategory) {
		categoryRepository.save(toCategoryEntity(newCategory));
		
	}
	
	public CategoryDto findCategoryById(Long id) {

		var categoryFound = findRegisterCategoryById(id);
					
		return toCategoryDto(categoryFound);
	}
	
	public List<CategoryDto> findAllCategories() {
		var foundCategories = categoryRepository.findAll();
		
		if(foundCategories == null || foundCategories.size() == 0)
			new CategoryNotFoundException("Category not found");
				
			
		return foundCategories.stream().map(this::toCategoryDto).collect(Collectors.toList());
	}
	
	public void updateCompleteCategoryById(Long id,  CategoryCommand category) {
		
		var categoryFound = findRegisterCategoryById(id);
				
		categoryFound.setName(category.getName());
		categoryFound.setDescription(category.getDescription());
		categoryFound.setEnabled(category.getEnabled());
				
					
		categoryRepository.save(categoryFound);
	}
	
	public void updateCategoryById(Long id, Map<String, Object> changesInCategory) {
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
		 
		categoryRepository.save(categoryFound);
		 
	}
	
	private CategoryEntity findRegisterCategoryById(Long id) {
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
