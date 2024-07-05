package com.project.commerce.domain.joinEntity;

import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus orderItemStatus;

    public static OrderItem createOrderItem(Item item, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(quantity);
        orderItem.setOrderItemStatus(OrderItemStatus.ORDER);

        item.removeStock(quantity);

        return orderItem;
    }

    public static OrderItem mapCartItemToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(cartItem.getItem());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setOrderItemStatus(OrderItemStatus.ORDER);

        cartItem.getItem().removeStock(cartItem.getQuantity());
        return orderItem;
    }

    public int getPrice() {
        return quantity * item.getPrice();
    }

    public void cancel() {
        getItem().addStock(quantity);
    }
}