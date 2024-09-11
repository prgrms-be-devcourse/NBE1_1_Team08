package com.demo.coffeeshop.controller;

import com.demo.coffeeshop.model.dto.OrderItemUpdateDTO;
import com.demo.coffeeshop.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody List<OrderItemUpdateDTO> dtos){
        for (OrderItemUpdateDTO dto : dtos) {
            System.out.println(dto);
        }

        orderItemService.updateAll(dtos);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        return ResponseEntity.ok().body(response);
    }
}
