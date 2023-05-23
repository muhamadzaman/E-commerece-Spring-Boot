package com.ecommerece.userinterface.external.models;

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
