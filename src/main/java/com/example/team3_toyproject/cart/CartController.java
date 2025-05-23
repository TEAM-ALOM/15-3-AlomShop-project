package com.example.team3_toyproject.cart;

import com.example.team3_toyproject.cart.dto.CartResponse;
import com.example.team3_toyproject.product.Product;
import com.example.team3_toyproject.product.dto.ProductRequest;
import com.example.team3_toyproject.user.User;
import com.example.team3_toyproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<CartResponse> addProduct(@RequestBody ProductRequest request,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("그런 유저 없음"));

        Long userId = user.getId();
        CartResponse response = cartService.addProductToCart(userId, request.getProductId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("그런 유저 없음"));

        Long userId = user.getId();

        CartResponse response = cartService.getCart(userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSelectedProductsFromCart(@RequestBody List<Long> productIds,
                                                               @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();

        cartService.deleteProducts(user.getId(), productIds);
        return ResponseEntity.noContent().build();
    }


}
