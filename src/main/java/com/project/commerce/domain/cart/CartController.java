package com.project.commerce.domain.cart;

import com.project.commerce.domain.joinEntity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable("userId") Long userId) {
        return cartService.getCartItems(userId);
    }

    @PostMapping
    public String addCart(@RequestParam("userId") Long userId,
                          @RequestParam("itemId") Long itemId,
                          @RequestParam("count") int count) {
        cartService.addCart(userId, itemId, count);
        return "ok";
    }

    @PatchMapping
    public String setItemQuantity(@RequestParam("userId") Long userId,
                                  @RequestParam("itemId") Long itemId,
                                  @RequestParam("count") int count) {
        cartService.setItemQuantity(userId, itemId, count);
        return "ok";
    }


    @DeleteMapping("/{userId}")
    public String deleteCartItem(@PathVariable("userId") Long userId,
                                 @RequestParam("itemId") Long itemId) {
        cartService.deleteCartItem(userId, itemId);
        return "ok";
    }

    @DeleteMapping
    public String clearCart(@RequestParam("userId") Long userId) {
        cartService.clearCart(userId);
        return "ok";
    }
}
