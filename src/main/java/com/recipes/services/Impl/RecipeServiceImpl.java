package com.recipes.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.recipes.dtos.PageableResponse;
import com.recipes.dtos.RecipeDto;
import com.recipes.entities.Recipe;
import com.recipes.helper.helper;
import com.recipes.repositories.DietRepository;
import com.recipes.repositories.RecipeRepository;
import com.recipes.services.RecipeService;



@Service
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	public RecipeRepository reciperepository;
	
	@Autowired
	public DietRepository dietRepository;

	@Autowired
	public ModelMapper mapper;
	
	
	public PageableResponse<RecipeDto> getAllRecipes(int pageNumber,int pageSize, String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		
		Pageable pageble = PageRequest.of(pageNumber, pageSize,sort);
		
		   Page<Recipe> page = reciperepository.findAll(pageble);
		   PageableResponse<RecipeDto> response = helper.getPagebleResponse(page, RecipeDto.class);
		return response;
	}

	@Override
	public PageableResponse<RecipeDto> getAllRecipesByDistinctDietNames(int pageNumber, int pageSize, String sortBy, String sortDir) {
	    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

	    List<String> distinctDietNames = dietRepository.findDistinctDietNames();
	    Page<Recipe> page = reciperepository.findRecipesByDietNames(distinctDietNames, pageable);
	    
	    return helper.getPagebleResponse(page, RecipeDto.class);
	}

	@Override
	public PageableResponse<RecipeDto> getRecipesByDiets(String dietName, int pageNumber, int pageSize, String sortBy, String sortDir) {
		 Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		    Page<Recipe> page = reciperepository.findByDiet(dietName,pageable);
		    PageableResponse<RecipeDto> response = helper.getPagebleResponse(page, RecipeDto.class);
			return response;
	}

	@Override
	public RecipeDto getRecipeById(long recipeId) {
		Recipe recipe= reciperepository.findByRecipeId(recipeId);
		RecipeDto recipeDto = mapper.map(recipe, RecipeDto.class);
		return recipeDto;
	}


	@Override
	public PageableResponse<RecipeDto> getRecipeByCuisine(String cuisineName, int pageNumber, int pageSize,String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		 Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		    Page<Recipe> page = reciperepository.findbyCuisine(cuisineName,pageable);
		    PageableResponse<RecipeDto> response = helper.getPagebleResponse(page, RecipeDto.class);
		return response;
	}

	@Override
	public List<RecipeDto> searchRecipe(String keyword) {
		// TODO Auto-generated method stub
		List<Recipe> recipes = reciperepository.findByRecipeNameContaining(keyword);
		List<RecipeDto> recipeDto = recipes.stream()
				.map(recipe -> mapper.map(recipe, RecipeDto.class))
				.collect(Collectors.toList());
		return recipeDto;
	}
	 
	 public List<RecipeDto> getSuggestions(String keyword) {
			// TODO Auto-generated method stub
			List<Recipe> recipes = reciperepository.findByRecipeNameContainingIgnoreCase(keyword);
			List<RecipeDto> recipeDto = recipes.stream()
					.map(recipe -> mapper.map(recipe, RecipeDto.class))
					.collect(Collectors.toList());
			return recipeDto;
		}
}

