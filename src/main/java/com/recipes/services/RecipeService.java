package com.recipes.services;


import java.util.List;

import com.recipes.dtos.PageableResponse;
import com.recipes.dtos.RecipeDto;
import com.recipes.entities.Recipe;


public interface RecipeService {

	PageableResponse<RecipeDto> getAllRecipes(int pageNumber,int pageSize,String sortBy,String sortDir);

	public PageableResponse<RecipeDto> getAllRecipesByDistinctDietNames(int pageNumber, int pageSize, String sortBy, String sortDir);

	public PageableResponse<RecipeDto> getRecipesByDiets(String dietName, int pageNumber, int pageSize, String sortBy, String sortDir);

	public RecipeDto getRecipeById(long recipeId);

	public PageableResponse<RecipeDto> getRecipeByCuisine(String cuisineName,int pageNumber, int pageSize, String sortBy, String sortDir);

	public List<RecipeDto> searchRecipe(String keyword);
	
	public List<RecipeDto> getSuggestions(String keyword);
}

