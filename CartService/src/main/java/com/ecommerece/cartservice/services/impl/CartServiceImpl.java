package com.ecommerece.cartservice.services.impl;

import com.ecommerece.cartservice.entities.Cart;
import com.ecommerece.cartservice.entities.CartItem;
import com.ecommerece.cartservice.services.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Override
    public List<CartItem> addToCart(CartItem cartItem, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART_SESSION");
        if (cart == null) {
            cart = new Cart(session.getId());
        }
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.add(new CartItem(cartItem.getProductId(), cartItem.getQuantity()));
        cart.setCartItems(cartItems);
        session.setAttribute("CART_SESSION", cart);
        return cartItems;
    }

    @Override
    public List<CartItem> getCartItems(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("CART_SESSION");
        if (cart == null) {
            cart = new Cart(session.getId());
        }
        List<CartItem> cartItems = cart.getCartItems();
        return cartItems;
    }

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("CART_SESSION");
        if (cart == null) {
            cart = new Cart(session.getId());
        }
        return cart;
    }
}
