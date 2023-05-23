package com.ecommerece.productservice.controllers;

import com.cloudinary.Cloudinary;
import com.ecommerece.productservice.dtos.PostProductDto;
import com.ecommerece.productservice.dtos.ProductDto;
import com.ecommerece.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("p")
public class ProductController {
    private final ProductService productService;
    private final Cloudinary cloudinary;

    public ProductController(ProductService productService, Cloudinary cloudinary) {
        this.productService = productService;
        this.cloudinary = cloudinary;
    }
    @PostMapping(value = {"/users/{userId}/products"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductDto> createProduct(@PathVariable String userId, @Valid PostProductDto product) {

        ProductDto product1 = productService.saveProduct(userId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        ProductDto product = productService.getProduct(productId);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/users/{userId}/products")
    public ResponseEntity<List<ProductDto>> getUserProducts(@PathVariable String userId) {
        List<ProductDto> userProducts = productService.getUserProducts(userId);
        return ResponseEntity.ok(userProducts);
    }

    @PutMapping(value = "/products/{productId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ProductDto updateProduct(@PathVariable String productId ,@Valid PostProductDto product){
        return productService.updateProduct(productId,product);
    }

    @PatchMapping("/products/{productId}")
    public ProductDto updateProductFields(@PathVariable String productId ,@RequestBody Map<String, Object> product){
        return productService.updateProductFields(product ,productId);
    }
    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
    }

}
