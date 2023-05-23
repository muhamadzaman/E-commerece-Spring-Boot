package com.ecommerece.cartservice.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem implements Serializable {
    private String productId;
    private int quantity;
}
