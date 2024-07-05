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
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private CartItemStatus cartItemStatus;

    public static CartItem createCartItem(Item item, int count) {
        return CartItem.builder()
                .item(item)
                .quantity(count)
                .cartItemStatus(CartItemStatus.CART)
                .build();
    }
}
