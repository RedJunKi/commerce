package com.project.commerce.domain.order;


import com.project.commerce.domain.joinEntity.CartOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrders(Long memberId) {
        List<Order> orders = orderService.getOrders(memberId);
        return "ok";
    }

    @GetMapping("/{id}")
    public String getOrderDetail(Long orderId) {
        List<CartOrderItem> cartOrderItems = orderService.getOrder(orderId);
        return "ok";
    }

    @PostMapping
    public String singleOrder(Long userId, Long itemId, int count) {
        orderService.addCart(userId, itemId, count);
        return "ok";
    }


}
