package com.demo.coffeeshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemUpdateDTO {
    private Long id; // product id
    private int quantity;
}
