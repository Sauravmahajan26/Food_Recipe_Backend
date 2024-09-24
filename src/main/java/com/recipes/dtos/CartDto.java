package com.recipes.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.recipes.entities.CartItem;
import com.recipes.entities.User;

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
public class CartDto {
	private String cartId;
    private Date createdAt;
    private userDto user;
    private List<CartItemDto> items = new ArrayList<>();
}
