package com.example.shop.order.service;

import com.example.shop.order.entity.Order;
import com.example.shop.order.dto.OrderCreateRequest;

import java.util.List;

public interface OrderService {

    Long createOrder(OrderCreateRequest request);

    List<Order> getAllOrders();

    void cancelOrder(Long orderId);
}
