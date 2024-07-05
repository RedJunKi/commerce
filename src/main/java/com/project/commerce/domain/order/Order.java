package com.project.commerce.domain.order;

import com.project.commerce.domain.common.BaseTimeEntity;
import com.project.commerce.domain.joinEntity.CartItem;
import com.project.commerce.domain.joinEntity.OrderItem;
import com.project.commerce.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDERS")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }

    public static Order createOrder(User user, OrderItem orderItem) {
        Order order = new Order();
        order.setUser(user);
        order.addOrderItem(orderItem);
        order.setOrderStatus(OrderStatus.ORDERED);

        return order;
    }

    public static Order createOrder(User user, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUser(user);
        orderItems.forEach(order::addOrderItem);
        order.setOrderStatus(OrderStatus.ORDERED);

        return order;
    }

    public void cancel() {
        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            throw new RuntimeException("배송중인 상품은 취소가 불가능합니다.");
        }

        this.setOrderStatus(OrderStatus.CANCELED);
        orderItems.stream()
                .forEach(OrderItem::cancel);
    }
}
