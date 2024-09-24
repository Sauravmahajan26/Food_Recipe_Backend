package com.recipes.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.dtos.DietDto;

import com.recipes.entities.Diet;
import com.recipes.repositories.DietRepository;
import com.recipes.services.DietService;

@Service
public class DietServiceImpl implements DietService{

	@Autowired
	public DietRepository dietRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public List<DietDto> getAllDiets() {
		List<Diet> diets = dietRepository.findAll();
		return diets.stream()
				.map(diet -> modelMapper.map(diet, DietDto.class))
				.collect(Collectors.toList());
	}

	@Override
	 public List<DietDto> getDietsByDietary() {
        // Fetch data from repository
        List<DietDto> dietsByDietary = dietRepository.findByDietaryRestrictions();

        return dietsByDietary.stream()
        		.map(diet -> modelMapper.map(diet, DietDto.class))
				.collect(Collectors.toList());
    }

}
