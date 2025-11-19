package com.example.shop.order.repository;

import com.example.shop.order.entity.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(Long id);

    List<Order> findAll();

    void cancel(Long id);
}
