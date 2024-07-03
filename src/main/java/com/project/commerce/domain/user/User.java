package com.project.commerce.domain.user;

import com.project.commerce.domain.common.BaseTimeEntity;
import com.project.commerce.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();
}
