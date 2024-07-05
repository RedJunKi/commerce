package com.project.commerce.domain.cart;

import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.item.ItemRepository;
import com.project.commerce.domain.joinEntity.CartItem;
import com.project.commerce.domain.joinEntity.CartItemStatus;
import com.project.commerce.domain.user.User;
import com.project.commerce.domain.user.UserRepository;
import com.project.commerce.domain.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    public List<CartItem> getCartItems(Long userId) {
        User target = ObjectUtil.getObject(userRepository.findById(userId));
        return target.getCart().getCartItems();
    }


    public void addCart(Long userId, Long itemId, int count) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Item item = ObjectUtil.getObject(itemRepository.findById(itemId));
        Cart cart = user.getCart();

        CartItem cartItem = CartItem.createCartItem(item, count);
        cart.addCartItem(cartItem);
        cartRepository.save(cart);
    }

    public void setItemQuantity(Long userId, Long itemId, int count) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Cart cart = user.getCart();

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(i -> i.getItem().getId().equals(itemId))
                .findFirst().orElseThrow();
        
        cartItem.setQuantity(count);
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long userId, Long itemId) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Item item = ObjectUtil.getObject(itemRepository.findById(itemId));
        Cart cart = user.getCart();

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(i -> i.getItem().getId().equals(itemId))
                .findAny().orElseThrow();

        cartItem.setCartItemStatus(CartItemStatus.CANCEL);
        cartRepository.save(cart);
    }

    public void clearCart(Long userId) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Cart cart = user.getCart();
        cart.getCartItems()
                .forEach(i -> i.setCartItemStatus(CartItemStatus.CANCEL));
        cartRepository.save(cart);
    }
}
