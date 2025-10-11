package com.example.shop.order.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class OrderCreateRequest {

    private Long memberId;

    private List<OrderItemRequest> items;

    public OrderCreateRequest(Long memberId, List<OrderItemRequest> items) {
        this.memberId = memberId;
        this.items = items;
    }

    @Getter
    public static class OrderItemRequest {
        private Long productId;
        private Integer count;

        public OrderItemRequest(Long productId, Integer count) {
            this.productId = productId;
            this.count = count;
        }
    }
}