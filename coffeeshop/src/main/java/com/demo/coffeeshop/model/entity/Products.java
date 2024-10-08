package com.demo.coffeeshop.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private long price;

    private long stock;

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

    public Products(String productName, String catagory, long price, long stock, String description, String image_url) {
        this.productName = productName;
        this.category = catagory;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image_url = image_url;
    }

    public void changeInfo(String catagory, long price, long stock, String description, String image_url){
        this.category = catagory;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image_url = image_url;
    }

    public void changeStock(long stock){
        this.stock = stock;
    }

}
