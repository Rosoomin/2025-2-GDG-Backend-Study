package com.example.shop.product.dto;

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    @NotNull(message = ErrorMessage.PRODUCT_NAME_NOT_NULL)
    @Size(min = 1, max = 50, message = ErrorMessage.PRODUCT_NAME_SIZE)
    private String name;

    @NotNull(message = ErrorMessage.PRODUCT_PRICE_NOT_NULL)
    @Min(value = 0, message = ErrorMessage.PRODUCT_PRICE_MIN)
    private Integer price;

    @NotNull(message = ErrorMessage.PRODUCT_STOCK_NOT_NULL)
    @Min(value = 0, message = ErrorMessage.PRODUCT_STOCK_MIN)
    private Integer stockQuantity;

    public ProductCreateRequest(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
