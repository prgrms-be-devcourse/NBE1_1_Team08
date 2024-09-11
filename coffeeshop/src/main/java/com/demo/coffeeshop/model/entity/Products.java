package com.demo.coffeeshop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", columnDefinition = "BINARY(36)")
    private UUID productId;

    @Column(length = 20)
    private String productName;

    @Column(length = 50)
    private String category;

    private int price;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String image_url;

    @CreationTimestamp
    @Column(length = 6)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 6)
    private LocalDateTime updatedAt;

    public Products(String productName, String catagory, int price, String description, String image_url) {
        this.productName = productName;
        this.category = catagory;
        this.price = price;
        this.description = description;
        this.image_url = image_url;
    }

    public void changeInfo(String catagory, int price, String description, String image_url){
        this.category = catagory;
        this.price = price;
        this.description = description;
        this.image_url = image_url;
    }
}
