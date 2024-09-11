package com.demo.coffeeshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderRequestDTO {

    private String email;
    private String address;
    private String postcode;
    private List<OrderItem> orderItems;

    @Data
    public static class OrderItem {
        private UUID id; //product id
        private int quantity;
    }
}
