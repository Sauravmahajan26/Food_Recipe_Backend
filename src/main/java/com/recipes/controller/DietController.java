package com.recipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.dtos.DietDto;
import com.recipes.services.DietService;

@CrossOrigin("http://localhost:5173/")
@RequestMapping("/diets")
@RestController
public class DietController {
	
	@Autowired
	public DietService dietService;

	@GetMapping("/allDiets")
	public List<DietDto> getAllDiets(){
		List<DietDto> diets = dietService.getAllDiets();
		return diets;
	}
	
	@GetMapping("/getDietByDietary")
	public List<DietDto> getDietByDietary(){
		List<DietDto> dietaryDiets = dietService.getDietsByDietary();
		return dietaryDiets;
	}
	
}
