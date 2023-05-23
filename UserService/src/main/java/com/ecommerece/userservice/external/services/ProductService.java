package com.ecommerece.userservice.external.services;

import com.ecommerece.userservice.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {
    @GetMapping("users/{userId}/products")
    public ResponseEntity<List<ProductDto>> getUserProducts(@PathVariable String userId);
}
