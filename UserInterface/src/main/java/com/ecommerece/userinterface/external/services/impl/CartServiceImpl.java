package com.ecommerece.userinterface.external.services.impl;

import com.ecommerece.userinterface.external.dtos.GetProductDto;
import com.ecommerece.userinterface.external.models.Cart;
import com.ecommerece.userinterface.external.models.CartItem;
import com.ecommerece.userinterface.external.services.ApiResponse;
import com.ecommerece.userinterface.external.services.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    private final ApiResponse apiResponse;

    public CartServiceImpl(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    @Override
    public void addToCart(CartItem cartItem, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART_SESSION");
        if (cart == null) {
            cart = new Cart(session.getId());
        }
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(item -> Objects.equals(item.getProductId(), cartItem.getProductId()));
        cartItems.add(new CartItem(cartItem.getProductId(), cartItem.getQuantity()));
        cart.setCartItems(cartItems);
        session.setAttribute("CART_SESSION", cart);
    }

    @Override
    public void removeFromCart(String productId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("CART_SESSION");
        if (cart == null) {
            cart = new Cart(session.getId());
        }
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(item -> Objects.equals(item.getProductId(), productId));
    }

    @Override
    public HashMap<GetProductDto, Integer> getCartItems(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("CART_SESSION");
        if (cart == null) {
            cart = new Cart(session.getId());
        }
        HashMap<GetProductDto, Integer> products = new HashMap<>();
        List<CartItem> cartItems=   cart.getCartItems();
        for (CartItem cartItem: cartItems) {
            products.put(apiResponse.getProduct(cartItem.getProductId()), cartItem.getQuantity());
        }
        return products;
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
