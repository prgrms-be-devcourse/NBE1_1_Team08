package com.demo.coffeeshop.model.repository;

import com.demo.coffeeshop.model.entity.Orders;
import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {

    Orders findByOrderId(UUID uuid);
    List<Orders> findAllByEmail(String email);
    List<Orders> findAllByOrderStatus(OrderStatus status);
}
