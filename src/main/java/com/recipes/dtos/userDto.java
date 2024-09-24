package com.recipes.dtos;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class userDto {

    
    private String id; // Use Integer for nullable ID

    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    // Add password strength validation here
    private String password;

    @NotBlank(message = "Gender is required")
    // Consider using @Enumerated or custom validation for gender
    private String gender;

    
    private String about;
}
