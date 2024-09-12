package com.demo.coffeeshop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "orders")
@Entity(name = "order_items")
public class OrderItems {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Orders orders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(length = 50)
    private String category;

    private long price;

    private long quantity;

    @CreationTimestamp
    @Column(name = "created_at",length = 6)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", length = 6)
    private LocalDateTime updatedAt;

    public OrderItems(Products products, long quantity) {
        this.products = products;
        this.category = products.getCategory();
        this.price = products.getPrice();
        this.quantity = quantity;
    }

    public void changeQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setOrders(Orders order) {
        this.orders = order;
    }
}
