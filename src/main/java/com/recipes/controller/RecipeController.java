package com.recipes.controller;



import java.util.List;

import org.modelmapper.internal.bytebuddy.description.type.RecordComponentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.recipes.dtos.PageableResponse;
import com.recipes.dtos.RecipeDto;
import com.recipes.entities.Recipe;
import com.recipes.services.RecipeService;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	@Autowired
	public RecipeService recipeService;
	
	
	@GetMapping("/allRecipes")
	public ResponseEntity<PageableResponse<RecipeDto>> getAllRecipes(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "12",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "recipeId",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "desc",required = false) String sortDir
			){
		return new ResponseEntity<>(recipeService.getAllRecipes(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	
	 @GetMapping("/distinct-diet-recipes")
	    public ResponseEntity<PageableResponse<RecipeDto>> getAllRecipesByDistinctDietNames(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
				@RequestParam(value = "sortBy",defaultValue = "recipeId",required = false) String sortBy,
				@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir) {
		  PageableResponse<RecipeDto> response = recipeService.getAllRecipesByDistinctDietNames(pageNumber, pageSize, sortBy, sortDir);
	      return new ResponseEntity<PageableResponse<RecipeDto>>(response, HttpStatus.OK);

	    }
	
		@GetMapping("/Diet/Recipes/{DietName}")
		public ResponseEntity<PageableResponse<RecipeDto>> getRecipesByDiets(
				@PathVariable String DietName,
				@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
				@RequestParam(value = "sortBy",defaultValue = "recipeId",required = false) String sortBy,
				@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
				){
			return new ResponseEntity<>(recipeService.getRecipesByDiets(DietName,pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
		}
		
		@GetMapping("/Cuisine/Recipes/{CuisineName}")
		public ResponseEntity<PageableResponse<RecipeDto>> getRecipeByCuisines(@PathVariable String CuisineName,
				@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "30",required = false) int pageSize,
				@RequestParam(value = "sortBy",defaultValue = "recipeName",required = false) String sortBy,
				@RequestParam(value = "sortDir",defaultValue = "desc",required = false) String sortDir){
			
			return new ResponseEntity<>(recipeService.getRecipeByCuisine(CuisineName,pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
		}
		
		@GetMapping("/recipeById/{recipeId}")
		public ResponseEntity<RecipeDto> getRecipeById(@PathVariable long recipeId){
			RecipeDto recipedto = recipeService.getRecipeById(recipeId);
			return new ResponseEntity<>(recipedto,HttpStatus.OK);
		}
		
		@GetMapping("recipe/search/{keyword}")
		public ResponseEntity<List<RecipeDto>> getSearchRecipe(@PathVariable String keyword){
			List<RecipeDto> recipeDto = recipeService.searchRecipe(keyword);
			return new ResponseEntity<>(recipeDto,HttpStatus.OK);
		}
		
		@GetMapping("recipe/suggestions/{keyword}")
		public ResponseEntity<List<RecipeDto>> getSuggestions(@PathVariable String keyword){
			List<RecipeDto> recipeDto = recipeService.getSuggestions(keyword);
			return new ResponseEntity<>(recipeDto,HttpStatus.OK);
		}
}
