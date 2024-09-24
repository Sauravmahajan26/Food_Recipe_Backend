package com.recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipes.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
