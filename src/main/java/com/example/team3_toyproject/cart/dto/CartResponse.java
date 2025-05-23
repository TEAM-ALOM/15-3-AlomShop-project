package com.example.team3_toyproject.cart.dto;

import com.example.team3_toyproject.cart.domain.Cart;
import com.example.team3_toyproject.product.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {

    private Long id;
    List<CartItemResponse> itemResponses;

    public CartResponse(Long id, List<CartItemResponse> itemResponses) {
        this.id = id;
        this.itemResponses = itemResponses;
    }
}
