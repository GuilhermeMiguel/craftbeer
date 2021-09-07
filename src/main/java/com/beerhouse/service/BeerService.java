package com.beerhouse.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beerhouse.command.BeerCommand;
import com.beerhouse.dto.BeerDto;
import com.beerhouse.entity.BeerEntity;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.repository.BeerRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public BeerDto registerBeer(BeerCommand beer) {
		var foundCategory = categoryService.findRegisterCategoryById(beer.getIdCategory());
		
		var beerEntity = toBeerEntity(beer);
		
		beerEntity.setCategory(foundCategory);
		beerEntity.setId(null);
		
		var savedBeer = beerRepository.save(beerEntity);
		
		return toBeerDto(savedBeer);
	}

	public BeerDto findBeerById(Long id) {

		var foundBeer = findRegisterBeerById(id);
				
		return toBeerDto(foundBeer);
	}
	
	public List<BeerDto> findAllBeerWithFilters(String nameSearch, Long idCategory, Integer page, Integer size) {
		
		List<BeerEntity> foundBeers = new ArrayList<>();
		
		if(page != null && size != null) 
			foundBeers = beerRepository.findAllBeerByNameAndCategoryId(nameSearch, idCategory, PageRequest.of(page - 1, size))
				.orElseThrow(() ->	new BeerNotFoundException("Beers not found for this parameters"));
	
		else 
			foundBeers = beerRepository.findAllBeerByNameAndCategoryId(nameSearch, idCategory)
				.orElseThrow(() ->	new BeerNotFoundException("Beers not found for this parameters"));
		
		return foundBeers.stream().map(this::toBeerDto).collect(Collectors.toList());
	}
	
	public BeerDto updateCompleteBeerById(Long id,  BeerCommand beer) {
		
		var foundCategory = categoryService.findRegisterCategoryById(beer.getIdCategory());
		
		var beerFound = findRegisterBeerById(id);
				
			beerFound.setName(beer.getName());
			beerFound.setMilliliters(beer.getMilliliters());
			beerFound.setCategory(foundCategory);
			beerFound.setAlcoholContent(beer.getAlcoholContent());
			beerFound.setPrice(beer.getPrice());
				
		var beerUpdated = beerRepository.save(beerFound);
		
		return toBeerDto(beerUpdated);
	}

	public BeerDto updateBeerById(Long id, Map<String, Object> changesInBeer) {
		var beerFound = findRegisterBeerById(id);
		
		changesInBeer.forEach(
	                (change, value) -> {
	                    switch (change){
	                        case "name": beerFound.setName((String) value); break;
	                        case "milliliters": beerFound.setMilliliters((Double) value); break;
	                        case "alcoholContent":  beerFound.setAlcoholContent((String) value); break;
	                        case "idCategory":  beerFound.setCategory(
	                        						categoryService.findRegisterCategoryById(((Integer) changesInBeer.get("idCategory")).longValue())
	                        					); break;
	                        case "price":  beerFound.setPrice(BigDecimal.valueOf((Double) value)); break;
	                    }
	                }
	        );
		 
		var beerUpdated = beerRepository.save(beerFound);
		 
		return toBeerDto(beerUpdated);
	}

	public void deleteBeerById(Long id) {
		beerRepository.deleteById(id);		
	}
	
	private BeerEntity findRegisterBeerById(Long id) {
		return beerRepository.findById(id)
				.orElseThrow(() ->	new BeerNotFoundException("Beer not found for this id: " + id));
	}
	
	private BeerDto toBeerDto(BeerEntity beerEntity) {
		return modelMapper.map(beerEntity, BeerDto.class);
	}
	
	private BeerEntity toBeerEntity(BeerCommand beerCommand) {
		return modelMapper.map(beerCommand, BeerEntity.class);
	}

}
