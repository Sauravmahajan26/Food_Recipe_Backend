package com.recipes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recipes.dtos.DietDto;

import com.recipes.entities.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

	
	
    @Query("SELECT DISTINCT  new com.recipes.dtos.DietDto(d.dietId, d.dietName) " +
            "FROM Recipe r " +
            "JOIN r.diet d " +
            "JOIN r.course c " +
            "WHERE d.dietName NOT IN ('Non Vegeterian', 'High Protein Non Vegetarian') " +
            "AND c.courseName NOT IN ('Dinner', 'Dessert')")
     List<DietDto> findByDietaryRestrictions();

    @Query("SELECT DISTINCT d.dietName " +
            "FROM Recipe r " +
            "JOIN r.diet d " +
            "JOIN r.course c " +
            "WHERE d.dietName NOT IN ('Non Vegeterian', 'High Protein Non Vegetarian') " +
            "AND c.courseName NOT IN ('Dinner', 'Dessert')")
     List<String> findDistinctDietNames();
}
