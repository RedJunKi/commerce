package com.project.commerce.domain.joinEntity;

import com.project.commerce.domain.cart.Cart;
import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private Integer quantity;

    public static CartOrderItem createCartItem(int count, Item item, Cart cart) {
        return CartOrderItem.builder()
                .cart(cart)
                .item(item)
                .quantity(count)
                .build();
    }
}
