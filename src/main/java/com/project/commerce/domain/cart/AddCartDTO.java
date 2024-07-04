package com.project.commerce.domain.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartDTO {
    private Long userId;
    private Long itemId;
    private Integer count;
}
