package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //@Transactional
    public Long createOrder(OrderCreateRequest request) {

        Member member = memberRepository.findById(request.getMemberId());
        if (member == null) {
            throw new RuntimeException("주문하려는 회원을 찾을 수 없습니다.");
        }

        List<OrderItem> orderItems = request.getItems().stream()
                .map(itemRequest -> {
                    Product product = productRepository.findById(itemRequest.getProductId());
                    if (product == null) {
                        throw new RuntimeException("주문하려는 상품을 찾을 수 없습니다.");
                    }

                    return new OrderItem(product, itemRequest.getCount());
                })
                .collect(Collectors.toList());

        Order order = new Order(member, orderItems);

        orderRepository.save(order);

        return order.getId();
    }

    //@Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //@Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다.");
        }

        return order;
    }

    //@Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new RuntimeException("취소하려는 주문을 찾을 수 없습니다.");
        }

        orderRepository.cancel(id);
    }
}
