package com.example.team3_toyproject.cart;

import com.example.team3_toyproject.cart.domain.Cart;
import com.example.team3_toyproject.cart.domain.CartItem;
import com.example.team3_toyproject.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}

