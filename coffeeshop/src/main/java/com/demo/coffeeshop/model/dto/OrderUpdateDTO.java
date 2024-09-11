package com.demo.coffeeshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderUpdateDTO {
    private UUID id;
    private String email;
    private String address;
    private String postcode;
}
