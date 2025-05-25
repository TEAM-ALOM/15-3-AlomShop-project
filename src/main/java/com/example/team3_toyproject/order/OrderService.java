package com.example.team3_toyproject.order;

import com.example.team3_toyproject.order.dto.OrderRequest;
import com.example.team3_toyproject.product.Product;
import com.example.team3_toyproject.product.ProductRepository;
import com.example.team3_toyproject.user.User;
import com.example.team3_toyproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(OrderRequest request) {
    // User user = userRepository.findById(request.getUserId()).orElseThrow();
    //Product product = productRepository.findById(request.getProductId()).orElseThrow();

    // OrderRequest orderRequest= new OrderRequest();
    //orderRequest.setUserId(user);
    //order.setProduct(product);
    //order.setOrderDate(request.getOrderDate());

    //return orderRepository.save(order);
        return orderRepository.save(new Order());
}

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrders(Optional<Long> orderId, Optional<Long> userId, Optional<Long> productId, Optional<LocalDate> orderDate) {
        // 조건 조합으로 쿼리할 수 있도록 구현 필요 (간단히 JPA Specification 또는 QueryDSL, 혹은 if문 분기)
        return orderRepository.findAll(); // 임시
    }
}

