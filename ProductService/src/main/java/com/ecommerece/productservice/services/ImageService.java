package com.ecommerece.productservice.services;

import com.ecommerece.productservice.entities.ImageModel;

import java.util.List;

public interface ImageService {
    ImageModel saveImage(String productId, ImageModel image);
    List<ImageModel> getProductImages(String productId);
    ImageModel getImage(Long imageId);
}
