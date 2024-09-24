package com.recipes.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {

    private long recipeId;
    private String recipeName;
    private String recipeImageUrl;
    private int prepTimeInMins;
    private String instructions;
    private CuisineDto cuisine;
    private CourseDto course;
    private DietDto diet;
    private List<IngredientDto> ingredients;

   
}
