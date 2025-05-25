package com.example.team3_toyproject.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private Long productId;
    private LocalDate orderDate;
}


