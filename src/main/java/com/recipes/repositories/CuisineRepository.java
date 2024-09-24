package com.recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipes.entities.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Long>{

}
