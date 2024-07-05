package com.project.commerce.domain.order;


import com.project.commerce.domain.joinEntity.CartItem;
import com.project.commerce.domain.joinEntity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public String getOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrders(userId);
        return "ok";
    }

    @GetMapping("/{userId}/{orderId}")
    public String getOrderDetail(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        List<OrderItem> orderItems = orderService.getOrder(orderId);
        return "ok";
    }

    @PostMapping
    public String singleItemOrder(@RequestParam("userId") Long userId,
                                  @RequestParam("itemId") Long itemId,
                                  @RequestParam("count") int count) {
        int price = orderService.singleItemOrder(userId, itemId, count);
        return price + "";
    }

    @PostMapping("/{userId}")
    public String cartOrder(@PathVariable("userId") Long userId) {
        int result = orderService.cartOrder(userId);
        return "" + result;
    }

}
