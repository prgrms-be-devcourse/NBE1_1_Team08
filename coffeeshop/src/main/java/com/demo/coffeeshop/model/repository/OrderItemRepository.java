package com.demo.coffeeshop.model.repository;

import com.demo.coffeeshop.model.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
    OrderItems findBySeq(Long id);
}
