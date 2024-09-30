package com.recipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.dtos.CuisineDto;
import com.recipes.services.CuisineService;

@CrossOrigin(origins = {"http://localhost:5173/","https://food-recipe-frontend-3w8a.onrender.com/"})
@RestController
@RequestMapping("/cuisines")
public class CuisineController {

	@Autowired
	public CuisineService cuisineService;
	
	@GetMapping("/allCuisines")
	public List<CuisineDto> getAllCuisine(){
		List<CuisineDto> cuisine = cuisineService.getAllCuisines();
		return cuisine;
	}
	
}
