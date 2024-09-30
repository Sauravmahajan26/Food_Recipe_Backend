package com.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.dtos.ApiResponceMessage;
import com.recipes.dtos.CartDto;
import com.recipes.services.CartService;

@CrossOrigin(origins = {"http://localhost:5173/","https://food-recipe-frontend-3w8a.onrender.com/"})
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	   @PostMapping("/carts/{userId}")
	   //done
	    public ResponseEntity<CartDto> addItemToCart(@PathVariable String userId, @RequestParam Long recipeId) {
	        CartDto cartDto = cartService.addItemToCart(userId, recipeId);
	        return new ResponseEntity<>(cartDto, HttpStatus.OK);
	    }
	   
	   @DeleteMapping("/{userId}/item/{itemId}")
	   //done
	    public ResponseEntity<ApiResponceMessage> removeItemFromCart(@PathVariable String userId, @PathVariable int itemId) {
	        cartService.removeItemFromCart(userId, itemId);
	        ApiResponceMessage response = ApiResponceMessage.builder()
	                .message("Item is removed !!")
	                .success(true)
	                .status(HttpStatus.OK)
	                .build();
	        return new ResponseEntity<>(response, HttpStatus.OK);

	    }
	   
	 //clear cart
	    @DeleteMapping("/clearCart/{userId}")
	    public ResponseEntity<ApiResponceMessage> clearCart(@PathVariable String userId) {
	        cartService.clearCart(userId);
	        ApiResponceMessage response = ApiResponceMessage.builder()
	                .message("Now cart is blank !!")
	                .success(true)
	                .status(HttpStatus.OK)
	                .build();
	        return new ResponseEntity<>(response, HttpStatus.OK);

	    }
	    
	    //add items to cart
	    //done
	    @GetMapping("/{userId}")
	    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
	        CartDto cartDto = cartService.getCartByUser(userId);
	        return new ResponseEntity<>(cartDto, HttpStatus.OK);
	    }
}
