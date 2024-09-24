package com.recipes.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeId;  // Use 'long' to match MySQL's 'bigint'

    @Column(name = "recipe_name", nullable = false)
    private String recipeName;  // CamelCase for Java field
 
    @Column(name = "recipeImageUrl", length = 255)  // Limit to VARCHAR(255)
    private String recipeImageUrl;
    
    @Column(name="description",length = 3000)
    private String description;
    

    @Column(name = "prep_time_in_mins")
    private int prepTimeInMins;


    @Column(name = "instructions", length = 5000)  // VARCHAR(1000) in the DB
    private String instructions;

   
    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

	
}
