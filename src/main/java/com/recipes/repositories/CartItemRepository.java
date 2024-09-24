package com.recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipes.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
