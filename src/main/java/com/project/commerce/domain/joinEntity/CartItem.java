package com.project.commerce.domain.joinEntity;

import com.project.commerce.domain.cart.Cart;
import com.project.commerce.domain.common.BaseTimeEntity;
import com.project.commerce.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int quantity;

}

