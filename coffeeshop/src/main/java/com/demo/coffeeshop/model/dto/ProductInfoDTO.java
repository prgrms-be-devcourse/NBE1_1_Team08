package com.demo.coffeeshop.model.dto;

import com.demo.coffeeshop.model.entity.Products;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductInfoDTO {
    private UUID productId;
    private String productName;
    private String category;
    private long price;
    private long stock;
    private String description;
    private String image_url;

    public ProductInfoDTO(Products products) {
        this.productId = products.getProductId();
        this.productName = products.getProductName();
        this.category = products.getCategory();
        this.price = products.getPrice();
        this.stock = products.getStock();
        this.description = products.getDescription();
        this.image_url = products.getImage_url();
    }
}
