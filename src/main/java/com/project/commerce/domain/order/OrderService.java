package com.project.commerce.domain.order;

import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.item.ItemRepository;
import com.project.commerce.domain.joinEntity.CartOrderItem;
import com.project.commerce.domain.user.User;
import com.project.commerce.domain.user.UserRepository;
import com.project.commerce.domain.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<CartOrderItem> getOrder(Long orderId) {
        Order order = ObjectUtil.getObject(orderRepository.findById(orderId));

        return order.getCartOrderItems();
    }

    public void addCart(Long userId, Long itemId, int count) {

        User user = ObjectUtil.getObject(userRepository.findById(userId));
        Item item = ObjectUtil.getObject(itemRepository.findById(itemId));

    }

    public void cartOrder(Long userId) {
        User user = ObjectUtil.getObject(userRepository.findById(userId));
        user.getCart();
    }
}
