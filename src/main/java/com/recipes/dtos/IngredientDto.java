package com.recipes.dtos;
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
public class IngredientDto {
    
    private int ingredientId;      // Auto-incremented primary key
    private String ingredientName; // Name of the ingredient
    private long recipeId;         // Recipe ID to which this ingredient belongs
}
