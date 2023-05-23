package com.ecommerece.userinterface.external.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String sessionId;
    private List<CartItem> cartItems = new ArrayList<>();
    public Cart(String sessionId) {
        this.sessionId = sessionId;
    }
}
