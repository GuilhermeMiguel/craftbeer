package com.beerhouse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.command.BeerCommand;
import com.beerhouse.dto.BeerDto;
import com.beerhouse.entity.BeerEntity;
import com.beerhouse.exception.BeerException;
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

		var foundBeer = beerRepository.findById(id).orElseThrow(() ->
        	new BeerException("Beer bot found"));
				
		return toBeerDto(foundBeer);
	}
	
	public List<BeerDto> findAllBeers() {
		var foundBeer = beerRepository.findAll();
		
		if(foundBeer == null || foundBeer.size() == 0)
			new BeerException("Beer bot found");
		
		//BeerDto map = modelMapper(foundBeer, BeerDto.class);
			
		return foundBeer.stream().map(this::toBeerDto).collect(Collectors.toList());
	}
	
	
	private BeerDto toBeerDto(BeerEntity beerEntity) {
		return modelMapper.map(beerEntity, BeerDto.class);
	}
	
	private BeerEntity toBeerEntity(BeerCommand beerCommand) {
		return modelMapper.map(beerCommand, BeerEntity.class);
	}

}
