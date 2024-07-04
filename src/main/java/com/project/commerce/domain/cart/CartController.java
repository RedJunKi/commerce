package com.project.commerce.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public String getCart(Long userId) {
        return "ok";
    }

    @PostMapping
    public String addCart(@RequestBody AddCartDTO addCartDTO) {
        cartService.addCart(addCartDTO.getUserId(), addCartDTO.getItemId(), addCartDTO.getCount());
        return "ok";
    }
}
