package com.demo.coffeeshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@NoArgsConstructor
public class ProductAddDTO {
    private String productName;
    private String catagory;
    private int price;
    private String description;
    private String image_url;
}
