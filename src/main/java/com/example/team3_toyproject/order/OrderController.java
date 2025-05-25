package com.example.team3_toyproject.order;

import com.example.team3_toyproject.order.dto.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.status(201).body(orderService.createOrder(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrder(@RequestParam Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(@RequestParam Optional<Long> order_id,
                                                 @RequestParam Optional<Long> user_id,
                                                 @RequestParam Optional<Long> product_id,
                                                 @RequestParam Optional<LocalDate> order_date) {
        return ResponseEntity.ok(orderService.getOrders(order_id, user_id, product_id, order_date));
    }
}


