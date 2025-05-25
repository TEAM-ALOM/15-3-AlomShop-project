package com.example.team3_toyproject.order;

import com.example.team3_toyproject.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}



