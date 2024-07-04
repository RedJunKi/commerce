package com.project.commerce.domain.cart;

import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.item.ItemRepository;
import com.project.commerce.domain.joinEntity.CartOrderItem;
import com.project.commerce.domain.user.User;
import com.project.commerce.domain.user.UserRepository;
import com.project.commerce.domain.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    public List<CartOrderItem> getCart(Long userId) {
        User target = ObjectUtil.getObject(userRepository.findById(userId));
        return target.getCart().getCartOrderItems();
    }


    public void addCart(Long userId, Long itemId, int count) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Item item = ObjectUtil.getObject(itemRepository.findById(itemId));
        Cart cart = user.getCart();

        CartOrderItem cartItem = CartOrderItem.createCartItem(count, item, cart);
        cart.getCartOrderItems().add(cartItem);
    }


}
