package com.demo.coffeeshop.model.repository;

import com.demo.coffeeshop.model.entity.Orders;
import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {

    Orders findByOrderId(UUID uuid);
//    Page<Orders> findAll(Pageable pageable);
    Page<Orders> findAllByEmail(String email, Pageable pageable);
    Page<Orders> findAllByOrderStatus(OrderStatus status, Pageable pageable);

}
