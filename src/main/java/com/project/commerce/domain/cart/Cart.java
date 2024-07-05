package com.project.commerce.domain.cart;

import com.project.commerce.domain.common.BaseTimeEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private User user;


    @Builder.Default
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    public Cart(User user) {
        this.user = user;
        this.cartStatus = CartStatus.CART;
    }

    public void addCartItem(CartItem cartItem) {
        cartItem.setCart(this);
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItem.setCart(null);
        this.cartItems.remove(cartItem);
    }

    public void clear() {
        this.cartItems.clear();
    }
}
