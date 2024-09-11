package com.demo.coffeeshop.service;

import com.demo.coffeeshop.model.dto.ProductInfoDTO;
import com.demo.coffeeshop.model.dto.ProductUpdateDTO;
import com.demo.coffeeshop.model.entity.Products;
import com.demo.coffeeshop.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

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

    public Page<ProductInfoDTO> showProducts(Pageable pageable) {
        Page<Products> productsPage = productRepository.findAll(pageable);
        return productsPage.map(ProductInfoDTO::new);
    }

    public List<ProductInfoDTO> convertToProductInfoDTOs(List<Products> input){
        List<ProductInfoDTO> results = new ArrayList<>();

        for (Products products : input) {
            results.add(new ProductInfoDTO(products));
        }

        return results;
    }
}
