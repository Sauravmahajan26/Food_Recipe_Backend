package com.recipes.services;

import java.util.List;

import com.recipes.dtos.DietDto;

public interface DietService {

	public List<DietDto> getAllDiets();

	public List<DietDto> getDietsByDietary();
	
}
