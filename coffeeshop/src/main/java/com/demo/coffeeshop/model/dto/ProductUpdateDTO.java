package com.demo.coffeeshop.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductUpdateDTO {
    private UUID id;
    private String productName;
    private String catagory;
    private long price;
    private String description;
    private String image_url;
}
