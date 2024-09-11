package com.demo.coffeeshop.service;

import com.demo.coffeeshop.model.dto.OrderRequestDTO;
import com.demo.coffeeshop.model.dto.OrderUpdateDTO;
import com.demo.coffeeshop.model.entity.OrderItems;
import com.demo.coffeeshop.model.entity.Orders;
import com.demo.coffeeshop.model.entity.Products;
import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import com.demo.coffeeshop.model.repository.OrderRepository;
import com.demo.coffeeshop.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public UUID order(OrderRequestDTO orderRequest) {
        List<OrderItems> orderItems = new ArrayList<>();

        for (OrderRequestDTO.OrderItem item : orderRequest.getOrderItems()) {
            Products product = productRepository.findByProductId(item.getId());
            orderItems.add(new OrderItems(product, item.getQuantity()));
        }

        Orders order = Orders.createOrder(orderRequest.getEmail(),
                orderRequest.getAddress(),
                orderRequest.getPostcode(),
                orderItems);

        orderRepository.save(order);

        return order.getOrderId();
    }

    @Transactional
    public Orders update(OrderUpdateDTO dto){
        Orders order = orderRepository.findByOrderId(dto.getId());
        order.changeInfo(dto.getAddress(), dto.getPostcode());
        return orderRepository.save(order);
    }

    @Transactional
    public Orders updateStatus(UUID orderId, OrderStatus status){
        Orders order = orderRepository.findByOrderId(orderId);
        order.changeStatus(status);
        return orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(UUID orderId){
        Orders order = orderRepository.findByOrderId(orderId);
        order.cancel();
        orderRepository.save(order);
    }

    public List<Orders> findOrders(){
        return orderRepository.findAll();
    }

    public List<Orders> findByEmail(String email){
        return orderRepository.findAllByEmail(email);
    }

    public List<Orders> findByOrderStatus(OrderStatus status){
        return orderRepository.findAllByOrderStatus(status);
    }
}
