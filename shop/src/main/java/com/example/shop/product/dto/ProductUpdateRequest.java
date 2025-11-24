package com.example.shop.product.dto;

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductUpdateRequest {

    @Size(min = 1, max = 50, message = ErrorMessage.PRODUCT_NAME_SIZE)
    private String name;

    @Min(value = 0, message = ErrorMessage.PRODUCT_PRICE_MIN)
    private Integer price;

    @Min(value = 0, message = ErrorMessage.PRODUCT_STOCK_MIN)
    private Integer stockQuantity;

    public ProductUpdateRequest(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
