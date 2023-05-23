package com.ecommerece.productservice.external.services;

import com.ecommerece.productservice.dtos.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COMMENT-SERVICE")
public interface CommentService {
    @GetMapping("c/products/{productId}")
    ResponseEntity<List<CommentDto>> getProductComments(@PathVariable String productId);
}
