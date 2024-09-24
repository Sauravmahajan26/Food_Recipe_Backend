package com.recipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.dtos.CourseDto;
import com.recipes.dtos.CuisineDto;
import com.recipes.services.CourseService;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	public CourseService courseSerive;
	
	@GetMapping("/allCourses")
	public List<CourseDto> getAllCuisine(){
		List<CourseDto> courses = courseSerive.getAllCourses();
		return courses;
	}
		
}
