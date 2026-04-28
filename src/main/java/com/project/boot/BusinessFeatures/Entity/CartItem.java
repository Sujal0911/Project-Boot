package com.project.boot.BusinessFeatures.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Long productId;
}