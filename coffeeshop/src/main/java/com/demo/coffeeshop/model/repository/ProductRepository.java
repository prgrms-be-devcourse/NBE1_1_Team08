package com.demo.coffeeshop.model.repository;

import com.demo.coffeeshop.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, UUID> {

    Products findByProductId(UUID uuid);
}
