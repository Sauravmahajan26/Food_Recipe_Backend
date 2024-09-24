package com.recipes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dietId;  // Matches 'diet_id' field in the database

    @Column(name = "diet_name", nullable = false, length = 255)  // Matches 'diet_name' column
    private String dietName;

}
