package com.ecommerece.userinterface.external.services;

import com.ecommerece.userinterface.external.dtos.GetProductDto;
import com.ecommerece.userinterface.external.models.Cart;
import com.ecommerece.userinterface.external.models.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface CartService {
    public void addToCart(CartItem cartItem, HttpServletRequest request);
    public void removeFromCart(String productId, HttpSession session);
    public Map<GetProductDto, Integer> getCartItems(HttpSession session);
    public Cart getCart(HttpSession session);
}
