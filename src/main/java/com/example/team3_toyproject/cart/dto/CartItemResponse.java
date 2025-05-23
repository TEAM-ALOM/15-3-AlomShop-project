package com.example.team3_toyproject.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItemResponse {
    private Long productId;
    private int quantity;
}
