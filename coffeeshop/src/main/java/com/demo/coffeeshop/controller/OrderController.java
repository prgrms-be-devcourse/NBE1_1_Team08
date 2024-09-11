package com.demo.coffeeshop.controller;

import com.demo.coffeeshop.model.dto.OrderRequestDTO;
import com.demo.coffeeshop.model.dto.OrderUpdateDTO;
import com.demo.coffeeshop.model.entity.Orders;
import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import com.demo.coffeeshop.service.OrderService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<UUID> add(@RequestBody OrderRequestDTO dto){
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

    @GetMapping("/list")
    public ResponseEntity<List<Orders>> list(){
        List<Orders> orders = orderService.findOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/listEmail")
    public ResponseEntity<List<Orders>> listEmail(@RequestParam("email") String email){
        return ResponseEntity.ok().body(orderService.findByEmail(email));
    }

    @GetMapping("/listStatus")
    public ResponseEntity<List<Orders>> listStatus(@RequestParam("status") OrderStatus status){
        return ResponseEntity.ok().body(orderService.findByOrderStatus(status));
    }
}
