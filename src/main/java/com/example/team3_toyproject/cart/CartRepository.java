package com.example.team3_toyproject.cart;

import com.example.team3_toyproject.cart.domain.Cart;
import com.example.team3_toyproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
