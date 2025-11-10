package com.example.shop.order;

import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.product.Product;
import com.example.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    @Transactional
    public Long createOrder(OrderCreateRequest request) {

        Member member = memberRepository.findById(request.getMemberId());
        if (member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        // 총 주문 금액 = Σ(상품가격 * 수량)
        int totalPrice = request.getItems().stream()
                .mapToInt(item -> {
                    Product product = productRepository.findById(item.getProductId());
                    if (product == null) {
                        throw new RuntimeException("주문하려는 상품을 찾을 수 없습니다.");
                    }
                    return product.getPrice() * item.getCount();
                })
                .sum();

        Order order = new Order(member, totalPrice);
        orderRepository.save(order);

        return order.getId();
    }

    // 주문 내역 전체 조회
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다.");
        }
        order.cancel();   // 상태만 CANCELLED 로 변경
    }
}
