package com.demo.coffeeshop.controller;

import com.demo.coffeeshop.model.dto.ProductAddDTO;
import com.demo.coffeeshop.model.dto.ProductInfoDTO;
import com.demo.coffeeshop.model.dto.ProductUpdateDTO;
import com.demo.coffeeshop.model.entity.Products;
import com.demo.coffeeshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Products> add(@RequestBody ProductAddDTO dto) {
        Products product = productService.addProduct(new Products(dto.getProductName(),
                dto.getCatagory(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getImage_url()));

        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/update")
    public ResponseEntity<Products> update(@RequestBody ProductUpdateDTO dto) {
        Products product = productService.updateProduct(dto);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") UUID id) {
        productService.deleteProduct(id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductInfoDTO>> list(){
        List<ProductInfoDTO> dtos = productService.showProducts();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/popular")
    public List<ProductInfoDTO> popularList(){
        return productService.showPopularProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> view(@PathVariable UUID id){
        Products product = productService.showProductInfo(id);
        return ResponseEntity.ok().body(product);
    }
}
