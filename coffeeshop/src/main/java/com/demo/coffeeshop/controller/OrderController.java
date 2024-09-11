package com.demo.coffeeshop.controller;

import com.demo.coffeeshop.model.dto.OrderRequestDTO;
import com.demo.coffeeshop.model.dto.OrderUpdateDTO;
import com.demo.coffeeshop.model.entity.Orders;
import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import com.demo.coffeeshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/add")
    public ResponseEntity<UUID> add(@RequestBody OrderRequestDTO dto) throws Exception {
        UUID id = orderService.order(dto);
        return ResponseEntity.ok().body(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Orders> update(@RequestBody OrderUpdateDTO dto){
        Orders update = orderService.update(dto);
        return ResponseEntity.ok().body(update);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Orders> updateStatus(@RequestParam("id") UUID id, @RequestParam("status") OrderStatus status){
        Orders orders = orderService.updateStatus(id, status);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/cancle")
    public ResponseEntity<?> cancle(@RequestParam("id") UUID id){
        orderService.cancelOrder(id);
        return ResponseEntity.ok().body("sucess");
    }
// Page size의 기본은 10씩 createdAt
    @GetMapping("/list")
    public ResponseEntity<Page<Orders>> list(@PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Orders> orders = orderService.findOrders(pageable);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/listEmail")
    public ResponseEntity<Page<Orders>> listEmail(@RequestParam("email") String email, @PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Orders> orders = orderService.findByEmail(email, pageable);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/listStatus")
    public ResponseEntity<Page<Orders>> listStatus(@RequestParam("status") OrderStatus status, @PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Orders> orders = orderService.findByOrderStatus(status, pageable);
        return ResponseEntity.ok().body(orders);
    }
}
