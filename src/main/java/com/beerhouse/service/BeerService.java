package com.beerhouse.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ModelMapper modelMapper;
	
	public void registerBeer(BeerCommand beer) {
		beerRepository.save(toBeerEntity(beer));
	}

	public BeerDto findBeerById(Long id) {

		var foundBeer = findRegisterBeerById(id);
				
		return toBeerDto(foundBeer);
	}
	
	public List<BeerDto> findAllBeers() {
		var foundBeer = beerRepository.findAll();
		
		if(foundBeer == null || foundBeer.size() == 0)
			new BeerNotFoundException("Beers not found");
				
			
		return foundBeer.stream().map(this::toBeerDto).collect(Collectors.toList());
	}
	
	public void updateCompleteBeerById(Long id,  BeerCommand beer) {
		
		var beerFound = findRegisterBeerById(id);
				
				beerFound.setName(beer.getName());
				beerFound.setIngredients(beer.getName());
				beerFound.setCategory(beer.getCategory());
				beerFound.setAlcoholContent(beer.getAlcoholContent());
				beerFound.setPrice(beer.getPrice());
					
				beerRepository.save(beerFound);
	}

	public void updateBeerById(Long id, Map<String, Object> changesInBeer) {
		var beerFound = findRegisterBeerById(id);
		
		changesInBeer.forEach(
	                (change, value) -> {
	                    switch (change){
	                        case "name": beerFound.setName((String) value); break;
	                        case "ingredients": beerFound.setIngredients((String) value); break;
	                        case "alcoholContent":  beerFound.setAlcoholContent((String) value); break;
	                        case "category":  beerFound.setCategory((String) value); break;
	                        case "price":  beerFound.setPrice((BigDecimal) value); break;
	                    }
	                }
	        );
		 
		 beerRepository.save(beerFound);
		 
	}

	public void deleteBeerById(Long id) {
		beerRepository.deleteById(id);		
	}
	
	private BeerEntity findRegisterBeerById(Long id) {
		return beerRepository.findById(id)
				.orElseThrow(() ->	new BeerNotFoundException("Beer bot found"));
	}
	
	private BeerDto toBeerDto(BeerEntity beerEntity) {
		return modelMapper.map(beerEntity, BeerDto.class);
	}
	
	private BeerEntity toBeerEntity(BeerCommand beerCommand) {
		return modelMapper.map(beerCommand, BeerEntity.class);
	}

}
