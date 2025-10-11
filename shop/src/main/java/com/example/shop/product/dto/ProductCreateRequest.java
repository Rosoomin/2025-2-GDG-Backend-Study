package com.example.shop.product.dto;

import lombok.Getter;

@Getter
public class ProductCreateRequest {

    private String name;
    private Integer price;
    private Integer stockQuantity;

    public ProductCreateRequest(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}