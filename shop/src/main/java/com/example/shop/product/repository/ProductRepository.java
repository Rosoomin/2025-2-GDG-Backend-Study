package com.example.shop.product.repository;

import com.example.shop.product.entity.Product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    Product findById(Long id);

    Product findByName(String name);

    List<Product> findAll();

    void deleteById(Long id);
}
