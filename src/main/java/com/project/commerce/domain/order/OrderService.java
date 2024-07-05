package com.project.commerce.domain.order;

import com.project.commerce.domain.cart.Cart;
import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.item.ItemRepository;
import com.project.commerce.domain.joinEntity.CartItem;
import com.project.commerce.domain.joinEntity.CartItemStatus;
import com.project.commerce.domain.joinEntity.OrderItem;
import com.project.commerce.domain.user.User;
import com.project.commerce.domain.user.UserRepository;
import com.project.commerce.domain.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public List<Order> getOrders(Long memberId) {
        User user = userRepository.findById(memberId).orElseThrow();
        return user.getOrderList();
    }

    public List<OrderItem> getOrder(Long orderId) {
        Order order = ObjectUtil.getObject(orderRepository.findById(orderId));

        return order.getOrderItems();
    }

    public int cartOrder(Long userId) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        List<OrderItem> orderItems = cartItems.stream()
                .filter(c -> c.getCartItemStatus().equals(CartItemStatus.CART))
                .map(OrderItem::mapCartItemToOrderItem)
                .toList();

        Order order = Order.createOrder(user, orderItems);
        orderRepository.save(order);
        return order.getTotalPrice();
    }

    public int singleItemOrder(Long userId, Long itemId, int count) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Item item = ObjectUtil.getObject(itemRepository.findById(itemId));

        OrderItem orderItem = OrderItem.createOrderItem(item, count);

        Order order = Order.createOrder(user, orderItem);

        orderRepository.save(order);
        return order.getTotalPrice();
    }
}
