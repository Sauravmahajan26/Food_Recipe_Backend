package com.recipes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipes.entities.Cart;
import com.recipes.entities.User;

public interface CartRepository extends JpaRepository<Cart, String> {


    Optional<Cart> findByUser(User user);

}
