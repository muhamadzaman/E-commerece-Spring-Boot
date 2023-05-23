package com.ecommerece.userinterface.external.services;

import com.ecommerece.userinterface.external.dtos.CommentDto;
import com.ecommerece.userinterface.external.dtos.GetProductDto;
import com.ecommerece.userinterface.external.dtos.PostProductDto;
import com.ecommerece.userinterface.external.models.Cart;
import com.ecommerece.userinterface.external.models.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "API-GATEWAY")
public interface ApiResponse {
    @GetMapping("p/products")
    public List<GetProductDto> getAllProduct();
    @GetMapping("p/users/{userId}/products")
    public List<GetProductDto> getUserProducts(@PathVariable String userId);
    @GetMapping("p/products/{productId}")
    public GetProductDto getProduct(@PathVariable String productId);
    @PostMapping(value = {"p/users/{userId}/products"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public GetProductDto createProduct(@PathVariable String userId, PostProductDto product);
    @PutMapping(value = "p/products/{productId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public GetProductDto updateProduct(@PathVariable String productId, PostProductDto postProductDto);
    @DeleteMapping("p/products/{productId}")
    public void deleteProduct(@PathVariable String productId);

    // Comments
    @PostMapping("c/products/{productId}")
    public CommentDto createComment(@PathVariable String productId, CommentDto comment);
    @DeleteMapping("c/comments/{commentId}")
    public void deleteComment(@PathVariable String commentId);

    // Cart
//    @PostMapping("cart/add-item")
//    public List<CartItem> addToCart(CartItem cartItem, HttpServletRequest request);
    @GetMapping("cart/cart-items")
    public List<CartItem> cartItems();
    @GetMapping("cart/get-cart")
    public Cart getCart();

}
