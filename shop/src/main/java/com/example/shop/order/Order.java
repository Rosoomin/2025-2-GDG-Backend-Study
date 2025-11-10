package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "point_used")
    private int pointUsed;

    @Column(name = "cash_amount")
    private int cashAmount;

    @Column(name = "status", length = 25)
    private String status;

    // 주문 생성용 생성자 (필요 최소)
    public Order(Member member, int totalPrice) {
        this.member = member;
        this.orderDate = LocalDateTime.now();
        this.totalPrice = totalPrice;
        this.pointUsed = 0;
        this.cashAmount = totalPrice;
        this.status = "ORDERED";
    }

    // 주문 취소 (서비스에서 사용할 최소 메서드 1개)
    public void cancel() {
        this.status = "CANCELLED";
    }
}

