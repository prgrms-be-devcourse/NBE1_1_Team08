package com.demo.coffeeshop.model.dto;

import com.demo.coffeeshop.model.entity.Products;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductInfoDTO {
    private UUID productId;
    private String productName;
    private String catagory;
    private int price;
    private String description;
    private String image_url;

    public ProductInfoDTO(Products products) {
        this.productId = products.getProductId();
        this.productName = products.getProductName();
        this.catagory = products.getCategory();
        this.price = products.getPrice();
        this.description = products.getDescription();
        this.image_url = products.getImage_url();
    }
}
