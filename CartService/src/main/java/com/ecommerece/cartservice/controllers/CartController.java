package com.ecommerece.cartservice.controllers;

import com.ecommerece.cartservice.entities.Cart;
import com.ecommerece.cartservice.entities.CartItem;
import com.ecommerece.cartservice.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-item")
    public ResponseEntity<List<CartItem>> addToCart(@RequestBody CartItem cartItem, HttpServletRequest request) {
        return new ResponseEntity<>(cartService.addToCart(cartItem,request), HttpStatus.CREATED);
    }

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItem>> getCartItems(HttpSession session) {
        return new ResponseEntity<>(cartService.getCartItems(session), HttpStatus.OK);
    }

    @GetMapping("/get-cart")
    public ResponseEntity<Cart> getCart(HttpSession session) {
        return new ResponseEntity<>(cartService.getCart(session), HttpStatus.OK);
    }

    @DeleteMapping("/empty-cart")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request) {
        //clears session
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
