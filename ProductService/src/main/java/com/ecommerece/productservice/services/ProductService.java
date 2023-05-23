package com.ecommerece.productservice.services;

import com.ecommerece.productservice.dtos.PostProductDto;
import com.ecommerece.productservice.dtos.ProductDto;
import com.ecommerece.productservice.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductDto saveProduct(String userId, PostProductDto product);
    List<ProductDto> getAllProducts();
    List<ProductDto> getUserProducts(String userId);
    ProductDto getProduct(String productId);
    void deleteProduct(String productid);
    ProductDto updateProduct(String productid,PostProductDto product);
    ProductDto updateProductFields(Map<String, Object> fields, String id);


    //TODO: handle null in delete
    //TODO: update
}
