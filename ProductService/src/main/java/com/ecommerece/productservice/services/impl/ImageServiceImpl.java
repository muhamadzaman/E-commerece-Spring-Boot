package com.ecommerece.productservice.services.impl;

import com.ecommerece.productservice.entities.ImageModel;
import com.ecommerece.productservice.exceptions.ResourceNotFoundException;
import com.ecommerece.productservice.repositories.ImageRepository;
import com.ecommerece.productservice.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ModelMapper modelMapper, ImageRepository imageRepository) {
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageModel saveImage(String productId, ImageModel image) {
        image.setProductId(productId);
        return imageRepository.save(image);
    }

    @Override
    public List<ImageModel> getProductImages(String productId) {
        return imageRepository.findAllByProductId(productId);
    }

    @Override
    public ImageModel getImage(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("Image with given id is not found on server !! : " + imageId));
    }

//    private ImageDto getImageDtofromImage(Image image){
//        return modelMapper.map(image, ImageDto.class);
//    }
//
//    private Image getImagefromImageDto(ImageDto image){
//        return modelMapper.map(image, Image.class);
//    }
}
