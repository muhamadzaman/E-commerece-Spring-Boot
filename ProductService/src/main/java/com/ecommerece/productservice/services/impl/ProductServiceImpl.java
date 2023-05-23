package com.ecommerece.productservice.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerece.productservice.dtos.CommentDto;
import com.ecommerece.productservice.dtos.PostProductDto;
import com.ecommerece.productservice.dtos.ProductDto;
import com.ecommerece.productservice.entities.ImageModel;
import com.ecommerece.productservice.entities.Product;
import com.ecommerece.productservice.exceptions.ResourceNotFoundException;
import com.ecommerece.productservice.external.services.CommentService;
import com.ecommerece.productservice.repositories.ProductRepository;
import com.ecommerece.productservice.services.ImageService;
import com.ecommerece.productservice.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CommentService commentService;

    private final ImageService imageService;
    private final Cloudinary cloudinary;
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CommentService commentService, ImageService imageService, Cloudinary cloudinary) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.imageService = imageService;
        this.cloudinary = cloudinary;
    }

    @Override
    public ProductDto saveProduct(String userId, PostProductDto postProductDto) {
        ProductDto product = modelMapper.map(postProductDto, ProductDto.class);
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        product.setId(randomUserId);
        product.setUserId(userId);
        try {
            List<ImageModel> images = uploadImage(postProductDto.getImageFiles(), product.getId());
            product.setImages(images);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return getProductDtofromProduct(productRepository.save(getProductfromProductDto(product)));
    }
    @Override
    public ProductDto updateProduct(String productId, PostProductDto postProductDto) {
        ProductDto product = modelMapper.map(postProductDto, ProductDto.class);
        product.setId(productId);
        ProductDto product1 = getProduct(productId);
        if (!postProductDto.getImageFiles()[0].isEmpty())
        {
            try {
                List<ImageModel> images = uploadImage(postProductDto.getImageFiles(), product.getId());
                product.setImages(images);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            product.setImages(product1.getImages());
        }
        return getProductDtofromProduct(productRepository.save(getProductfromProductDto(product)));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        List<ProductDto> productDtos = productRepository.findAll().stream().map(this::getProductDtofromProduct).toList();
        return productDtos.stream().map( productDto -> {
            List<CommentDto> commentDtos = commentService.getProductComments(productDto.getId()).getBody();
                productDto.setComments(commentDtos);
            return productDto;
        }).toList();
//        return productDtos;
    }
    @Override
    public List<ProductDto> getUserProducts(String userId) {
        List<ProductDto> productDtos = productRepository.findAllByUserId(userId).stream().map(this::getProductDtofromProduct).toList();
//        return productDtos.stream().map( productDto -> {
//                List<CommentDto> commentDtos = commentService.getProductComments(productDto.getId()).getBody();
//                productDto.setComments(commentDtos);
//                return productDto;
//        }).toList();
        return productDtos;
    }
    @Override
    public ProductDto getProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given id is not found on server !! : " + productId));
        List<CommentDto> comments = commentService.getProductComments(product.getId()).getBody();
        ProductDto productDto = getProductDtofromProduct(product);
        productDto.setComments(comments);
        return productDto;
    }
    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto updateProductFields(Map<String, Object> fields, String id) {
        ProductDto currentProduct = getProduct(id);
        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(ProductDto.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,currentProduct,value);
        });
        return getProductDtofromProduct(productRepository.save(getProductfromProductDto(currentProduct)));
    }

    private List<ImageModel> uploadImage(MultipartFile[] multipartFiles, String productId) throws IOException {
//        String imagePath = "/home/dev/Downloads/AyubAhmad.png";
        List<ImageModel> imageModels = new ArrayList<ImageModel>();
        for (MultipartFile file : multipartFiles) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            ImageModel imageModel = new ImageModel(
                    uploadResult.get("secure_url").toString(),
                    productId
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }
    private ProductDto getProductDtofromProduct(Product product){
       return modelMapper.map(product, ProductDto.class);
    }

    private Product getProductfromProductDto(ProductDto product){
        return modelMapper.map(product, Product.class);
    }
//    private Product getProductfromPostProductDto(PostProductDto product){
//        return modelMapper.map(product, Product.class);
//    }
}
