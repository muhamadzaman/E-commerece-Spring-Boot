package com.ecommerece.userinterface.controllers;

import com.ecommerece.userinterface.external.dtos.CommentDto;
import com.ecommerece.userinterface.external.dtos.GetProductDto;
import com.ecommerece.userinterface.external.dtos.PostProductDto;
import com.ecommerece.userinterface.external.services.ApiResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CommentController {
    private final ApiResponse apiResponse;

    public CommentController(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    @PostMapping("/products/{productId}")
    public String createComment(@PathVariable String productId, @ModelAttribute CommentDto comment){
        CommentDto commentDto = new CommentDto();
        try {
            commentDto = apiResponse.createComment(productId, comment);
        }catch (FeignException e){
            System.out.println(e.getMessage());
            return "redirect:/products/" + productId;
        }
        return "redirect:/products/" + productId;
    }
    @DeleteMapping("products/{productId}/comments/{commentId}")
    public String deleteComment(@PathVariable String productId,@PathVariable String commentId){
        apiResponse.deleteComment(commentId);
        return ("redirect:/products/" + productId );
    }

}
