package com.recipes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recipes.entities.Recipe;

@Repository
public interface RecipeRepository  extends JpaRepository<Recipe,Long>{

	List<Recipe> findByRecipeNameContaining(String keyword);
	
	List<Recipe> findByRecipeNameContainingIgnoreCase(String keyword);
	
	@Query("SELECT r FROM Recipe r " +
	           "JOIN r.diet d " +
	           "JOIN r.course c " +
	           "WHERE d.dietName IN :dietNames " +
	           "AND d.dietName NOT IN ('Non Vegeterian', 'High Protein Non Vegetarian') " +
	           "AND c.courseName NOT IN ('Dinner', 'Dessert')")
	Page<Recipe> findRecipesByDietNames(List<String> dietNames, Pageable pageable);

	
	@Query("SELECT r FROM Recipe r WHERE r.diet.dietName = :dietName")
	Page<Recipe> findByDiet(String dietName, Pageable pageable);


	Recipe findByRecipeId(long recipeId);


	@Query("SELECT r FROM Recipe r WHERE r.cuisine.cuisineName = :cuisineName") 
	Page<Recipe> findbyCuisine(String cuisineName, Pageable pageable);


	
}

