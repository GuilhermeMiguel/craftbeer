package com.beerhouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beerhouse.entity.BeerEntity;

@Repository
public interface BeerRepository extends JpaRepository<BeerEntity, Long> {

	@Query("SELECT t1 FROM BeerEntity t1  JOIN t1.category t2 WHERE (:name is null or t1.name = :name) and (:idCategory is null"
		      + " or t2.id = :idCategory)")
	Optional<List<BeerEntity>>  findAllBeerByNameAndCategoryId(String name, Long idCategory);
	
	@Query("SELECT t1 FROM BeerEntity t1  JOIN t1.category t2 WHERE (:name is null or t1.name = :name) and (:idCategory is null"
		      + " or t2.id = :idCategory)")
	Optional<List<BeerEntity>>  findAllBeerByNameAndCategoryId(String name, Long idCategory, Pageable pageable);

//	Optional<List<BeerEntity>> findAllBeerByCategoryId(Long idCategory);
}
