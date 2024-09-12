package com.demo.coffeeshop.model.repository;

import com.demo.coffeeshop.model.entity.Products;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, UUID> {

    Products findByProductId(UUID uuid);

    //    Page<Products> findAll(Pageable pageable);
    @Query("SELECT oi.products FROM order_items oi " +
            "WHERE oi.orders.orderStatus IN (com.demo.coffeeshop.model.entity.enums.OrderStatus.READY_FOR_DELIVERY, " +
            "com.demo.coffeeshop.model.entity.enums.OrderStatus.SHIPPED, " +
            "com.demo.coffeeshop.model.entity.enums.OrderStatus.SETTLED) " +
            "GROUP BY oi.products.productId ORDER BY SUM(oi.quantity) DESC")
    Page<Products> findPopularProducts(Pageable pageable);
}
