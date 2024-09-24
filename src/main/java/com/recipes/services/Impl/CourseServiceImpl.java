package com.recipes.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.dtos.CourseDto;
import com.recipes.entities.Course;
import com.recipes.repositories.CourseRepository;
import com.recipes.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	public CourseRepository courseRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CourseDto> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses.stream()
				.map(course -> modelMapper.map(course, CourseDto.class))
				.collect(Collectors.toList());
	}

}
