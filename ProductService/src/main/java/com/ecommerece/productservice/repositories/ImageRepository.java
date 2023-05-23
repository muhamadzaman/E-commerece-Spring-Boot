package com.ecommerece.productservice.repositories;

import com.ecommerece.productservice.entities.ImageModel;
import com.ecommerece.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    List<ImageModel> findAllByProductId(String productId);
}
