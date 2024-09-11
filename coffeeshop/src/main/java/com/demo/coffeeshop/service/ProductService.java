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
        List<Products> popularProductList = new ArrayList<>();
        int topNum = 3; // 인기 상품 수
        List<Orders> ordersList = orderRepository.findAll();
        Map<UUID, Long> checkProducts = new HashMap<>();

        for (Products product : productRepository.findAll()) {
            checkProducts.put(product.getProductId(), 0L);
        }

        for (Orders orders : ordersList) {
            List<OrderItems> orderItemsList = orders.getOrderItems();
            for (OrderItems orderItems : orderItemsList) {
                UUID productId = orderItems.getProducts().getProductId();
                Long quantity = orderItems.getQuantity();
                // 추후 삭제
                System.out.println(orderItems.getProducts().getProductId() + " " + orderItems.getProducts().getProductName() + " " +quantity);
                checkProducts.put(productId, checkProducts.get(productId) + quantity);
            }
        }

        List<Map.Entry<UUID, Long>> entryList = new ArrayList<>(checkProducts.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (int i = 0; i < topNum; i++) {
            popularProductList.add(productRepository.findByProductId(entryList.get(i).getKey()));
        }
        return convertToProductInfoDTOs(popularProductList);
    }

    public List<ProductInfoDTO> convertToProductInfoDTOs(List<Products> input){
        List<ProductInfoDTO> results = new ArrayList<>();

        for (Products products : input) {
            results.add(new ProductInfoDTO(products));
        }

        return results;
    }
}
