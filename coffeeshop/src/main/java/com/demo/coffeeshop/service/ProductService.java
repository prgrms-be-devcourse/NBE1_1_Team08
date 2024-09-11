package com.demo.coffeeshop.service;

import com.demo.coffeeshop.model.dto.ProductInfoDTO;
import com.demo.coffeeshop.model.dto.ProductUpdateDTO;
import com.demo.coffeeshop.model.entity.OrderItems;
import com.demo.coffeeshop.model.entity.Orders;
import com.demo.coffeeshop.model.entity.Products;
import com.demo.coffeeshop.model.repository.OrderRepository;
import com.demo.coffeeshop.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Products addProduct(Products product){
        return productRepository.save(product);
    }

    @Transactional
    public Products updateProduct(ProductUpdateDTO dto){
        Products product = productRepository.findByProductId(dto.getId());
        product.changeInfo(dto.getCatagory(), dto.getPrice(), dto.getDescription(), dto.getImage_url());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID uuid){
        productRepository.deleteById(uuid);
    }

    public Products showProductInfo(UUID uuid){
        return productRepository.findByProductId(uuid);
    }

    public List<ProductInfoDTO> showProducts(){
        return convertToProductInfoDTOs(productRepository.findAll());
    }

    public List<ProductInfoDTO> showPopularProducts(){
        int topNum = 3; // 인기 상품 수

        Map<UUID, Long> productSalesCount = calculateProductSalesCount();

        List<Products> popularProductList = productSalesCount.entrySet().stream()
                .sorted(Map.Entry.<UUID, Long>comparingByValue().reversed())
                .limit(topNum)
                .map(entry -> productRepository.findByProductId(entry.getKey()))
                .collect(Collectors.toList());

        return convertToProductInfoDTOs(popularProductList);
    }

    private Map<UUID, Long> calculateProductSalesCount() {
        Map<UUID, Long> productSalesCount = productRepository.findAll().stream()
                .collect(Collectors.toMap(Products::getProductId, product -> 0L));

        orderRepository.findAll().forEach(order -> {
            order.getOrderItems().forEach(orderItem -> {
                UUID productId = orderItem.getProducts().getProductId();
                productSalesCount.merge(productId, orderItem.getQuantity(), Long::sum);
            });
        });
        return productSalesCount;
    }

    public List<ProductInfoDTO> convertToProductInfoDTOs(List<Products> input){
        List<ProductInfoDTO> results = new ArrayList<>();

        for (Products products : input) {
            results.add(new ProductInfoDTO(products));
        }

        return results;
    }
}
