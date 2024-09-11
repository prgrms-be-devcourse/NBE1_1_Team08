package com.demo.coffeeshop.service;

import com.demo.coffeeshop.model.dto.OrderItemUpdateDTO;
import com.demo.coffeeshop.model.entity.OrderItems;
import com.demo.coffeeshop.model.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItems update(Long id, int quantity){
        OrderItems orderItem = orderItemRepository.findBySeq(id);
        orderItem.changeQuantity(quantity);

        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public void updateAll(List<OrderItemUpdateDTO> dtoList){
        List<OrderItems> updates = new ArrayList<>();

        for (OrderItemUpdateDTO dto : dtoList) {
            OrderItems orderItem = orderItemRepository.findBySeq(dto.getId());
            orderItem.changeQuantity(dto.getQuantity());
            updates.add(orderItem);
        }

        orderItemRepository.saveAll(updates);
    }
}
