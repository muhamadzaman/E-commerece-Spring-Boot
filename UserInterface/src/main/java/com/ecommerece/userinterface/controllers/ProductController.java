package com.ecommerece.userinterface.controllers;

import com.ecommerece.userinterface.external.dtos.CommentDto;
import com.ecommerece.userinterface.external.dtos.GetProductDto;
import com.ecommerece.userinterface.external.dtos.PostProductDto;
import com.ecommerece.userinterface.external.models.CartItem;
import com.ecommerece.userinterface.external.services.ApiResponse;
import feign.FeignException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    private final ApiResponse apiResponse;

    public ProductController(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<GetProductDto> productDtos = apiResponse.getAllProduct();
        model.addAttribute("products", productDtos);
        return "products/index";
    }
    @RequestMapping(value = "/users/{userId}/products", method = RequestMethod.GET)
    public String userIndex(Model model,@PathVariable String userId) {
        List<GetProductDto> productDtos = apiResponse.getUserProducts(userId);
        model.addAttribute("products", productDtos);
        return "products/index";
    }
    @GetMapping(value = "/products/{productId}")
    public String show(Model model, @PathVariable String productId) {
        GetProductDto productDto = apiResponse.getProduct(productId);
        CommentDto commentDto = new CommentDto();
        CartItem cartItem = new CartItem();
        model.addAttribute("product", productDto);
        model.addAttribute("newComment", commentDto);
        model.addAttribute("cartItem", cartItem);
        return "products/show";
    }
    @GetMapping("users/{userId}/products/new")
    public String newProduct(Model model, @PathVariable String userId) {
        model.addAttribute("product", new PostProductDto());
        model.addAttribute("userId", userId);
        return "products/new";
    }
    @RequestMapping(value = "/users/{userId}/products", method = RequestMethod.POST)
    public String create(@PathVariable String userId, @ModelAttribute PostProductDto product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "redirect:/products/new";
        GetProductDto productDto = new GetProductDto();
        try {
            productDto = apiResponse.createProduct(userId, product);
        }catch (FeignException e){
           System.out.println(e.getMessage());
           return "redirect:/products/new";
        }
        return "redirect:/products/" + productDto.getId();
    }
    @GetMapping("/products/{productId}/edit")
    public String editProduct(Model model, @PathVariable String productId) {
        GetProductDto productDto = apiResponse.getProduct(productId);
        model.addAttribute("product", productDto);
        return "products/edit";
    }
    @PutMapping(value = "/products/{productId}")
    public String updateProduct(@PathVariable String productId , @ModelAttribute PostProductDto postProductDto){
        GetProductDto getProductDto = apiResponse.updateProduct(productId, postProductDto);
        return "redirect:/";
    }
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable String productId){
        apiResponse.deleteProduct(productId);
        return ("redirect:/");
    }
}
