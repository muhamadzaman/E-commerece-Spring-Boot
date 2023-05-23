package com.ecommerece.productservice.repositories;

import com.ecommerece.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByUserId(String userId);
}
