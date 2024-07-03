package com.project.commerce.domain.cart;

import com.project.commerce.domain.common.BaseTimeEntity;
import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.joinEntity.CartItem;
import com.project.commerce.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart extends BaseTimeEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Enumerated
    private CartStatus cartStatus;
}
