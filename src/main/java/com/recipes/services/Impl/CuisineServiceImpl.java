package com.recipes.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.dtos.CuisineDto;
import com.recipes.entities.Cuisine;
import com.recipes.repositories.CuisineRepository;
import com.recipes.services.CuisineService;

@Service
public class CuisineServiceImpl implements CuisineService{
	
	@Autowired
	public CuisineRepository cuisineRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CuisineDto> getAllCuisines() {
        List<Cuisine> cuisines = cuisineRepository.findAll();
        return cuisines.stream()
                       .map(cuisine -> modelMapper.map(cuisine, CuisineDto.class))
                       .collect(Collectors.toList());
    }

}
