package com.example.team3_toyproject.cart;

import com.example.team3_toyproject.cart.domain.Cart;
import com.example.team3_toyproject.cart.domain.CartItem;
import com.example.team3_toyproject.cart.dto.CartItemResponse;
import com.example.team3_toyproject.cart.dto.CartResponse;
import com.example.team3_toyproject.product.Product;
import com.example.team3_toyproject.product.ProductRepository;
import com.example.team3_toyproject.user.User;
import com.example.team3_toyproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartResponse addProductToCart(Long userId, Long productId) {

        final int quantity = 1;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("그런 유저 없음"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user)));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("그런 상품 없음"));

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setQuantity(quantity);
            newCartItem.setProduct(product);
            cartItemRepository.save(newCartItem);
        }

        List<CartItemResponse> itemResponses = cart.getItems().stream()
                .map(item -> new CartItemResponse(
                        item.getProduct().getId(),
                        //item.getProduct().getPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new CartResponse(user.getId(), itemResponses);
    }

    public void deleteProducts(Long userId, List<Long> productIds) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("그런 유저 없음"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("카트 생성 안됨"));

        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.size()) {
            throw new RuntimeException("존재하지 않는 상품이 있음");
        }

        List<CartItem> cartItems = cart.getItems().stream()
                .filter(item -> productIds.contains(item.getProduct().getId()))
                .collect(Collectors.toList());

        cartItemRepository.deleteAll(cartItems);

    }

    public CartResponse getCart(Long userId, Long productId, Long categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("그런 유저 없음"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("카트 생성 안됨"));

        List<CartItemResponse> itemResponses = cart.getItems().stream()
                .filter(item -> productId == null || item.getProduct().getId().equals(productId))
                .filter(item -> categoryId == null || item.getProduct().getCategory().getId().equals(categoryId))
                .map(item -> new CartItemResponse(
                        item.getProduct().getId(),
                        //item.getProduct().getPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new CartResponse(user.getId(), itemResponses);
    }
}
