package com.ecommerece.userinterface.controllers;

import com.ecommerece.userinterface.external.dtos.GetProductDto;
import com.ecommerece.userinterface.external.models.Cart;
import com.ecommerece.userinterface.external.models.CartItem;
import com.ecommerece.userinterface.external.services.ApiResponse;
import com.ecommerece.userinterface.external.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    private final ApiResponse apiResponse;
    private final CartService cartService;

    public CartController(ApiResponse apiResponse, CartService cartService) {
        this.apiResponse = apiResponse;
        this.cartService = cartService;
    }
    @PostMapping("products/{productId}/add-to-cart")
    public String addToCart(@ModelAttribute CartItem cartItem, HttpServletRequest request){
        cartService.addToCart(cartItem, request);
        return "products/index";
    }
    @DeleteMapping("products/{productId}/remove-from-cart")
    public String removeFromCart(@PathVariable String productId, HttpSession session){
        cartService.removeFromCart(productId,session);
        return "cart/index";
    }
    @GetMapping("/cart-items")
    public String cartItems(HttpSession session, Model model){
        Map<GetProductDto, Integer> cartItems = cartService.getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        System.out.println(cartItems.keySet().size());
        return "cart/index";
    }
    @GetMapping("/get-cart")
    public Cart getCart(HttpSession session){
        return cartService.getCart(session);
    }
}
