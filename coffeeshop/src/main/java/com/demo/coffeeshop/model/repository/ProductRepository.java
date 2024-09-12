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
            "WHERE oi.orders.orderStatus <> 'ACCEPTED' " +
            "AND oi.orders.orderStatus <> 'PAYMENT_CONFIRMED' " +
            "AND oi.orders.orderStatus <> 'CANCELLED' " +
            "GROUP BY oi.products.productId ORDER BY SUM(oi.quantity) DESC")
    Page<Products> findPopularProducts(Pageable pageable);
}
