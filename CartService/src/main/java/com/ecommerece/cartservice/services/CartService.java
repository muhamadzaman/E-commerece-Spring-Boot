package com.ecommerece.cartservice.services;

import com.ecommerece.cartservice.entities.Cart;
import com.ecommerece.cartservice.entities.CartItem;
import org.apache.catalina.LifecycleState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    public List<CartItem> addToCart(CartItem cartItem,HttpServletRequest request);
    public List<CartItem> getCartItems(HttpSession session);
    public Cart getCart(HttpSession session);
}
