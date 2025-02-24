package com.project.commerce.domain.item;

import com.project.commerce.domain.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer stock;
    private String manufacturer;


    public void removeStock(int quantity) {
        if (stock < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        this.stock -= quantity;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }
}
