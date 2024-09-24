package com.recipes.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.dtos.CartDto;
import com.recipes.entities.Cart;
import com.recipes.entities.CartItem;
import com.recipes.entities.Recipe;
import com.recipes.entities.User;
import com.recipes.exceptions.ResourceNotFoundException;
import com.recipes.repositories.CartItemRepository;
import com.recipes.repositories.CartRepository;
import com.recipes.repositories.RecipeRepository;
import com.recipes.repositories.UserRepository;
import com.recipes.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    

    @Override
    public CartDto addItemToCart(String userId, Long recipeId) {

        // Fetch the recipe from the repository
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id: " + recipeId));

        // Fetch the user from db
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in database!"));

        // Try to find the cart for the user
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCartId(UUID.randomUUID().toString());
            newCart.setCreatedAt(new Date());
            newCart.setUser(user);
            return newCart;
        });

        // Perform cart operations
        // Check if the item is already in the cart
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems().stream()
            .map(item ->{ 
            Recipe recipeInCart = item.getRecipe(); // Get the recipe from cart item

            if (recipeInCart != null && recipeInCart.getRecipeId() == recipeId) {
                // Item already present in cart
                updated.set(true);
            }
            return item;
    		})
            .collect(Collectors.toList());

        // If not updated, add a new item to the cart
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .recipe(recipe)
                    .build();
            cart.getItems().add(cartItem);
        }

        // Save the cart back to the repository
        Cart updatedCart = cartRepository.save(cart);
        
        // Map the updated cart to CartDto and return it
        return mapper.map(updatedCart, CartDto.class);
    }


    @Override
	public void removeItemFromCart(String userId, int cartItem) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		CartItem cartitem1 =  cartItemRepository.findById(cartItem).orElseThrow(()-> new ResourceNotFoundException("cartitem is not found"));	
		cartItemRepository.delete(cartitem1);
		}

    @Override
    public void clearCart(String userId) {
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given user not found !!"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given user not found !!"));
        return mapper.map(cart, CartDto.class);
    }
}