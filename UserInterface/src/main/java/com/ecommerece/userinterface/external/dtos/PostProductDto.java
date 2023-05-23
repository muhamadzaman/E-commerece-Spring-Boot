package com.ecommerece.userinterface.external.dtos;

import com.ecommerece.userinterface.external.models.ImageModel;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostProductDto {
    private String id;
    private String productName;
    private String description;
    private int availableQuantity;
    private BigDecimal price;
    private String userId;
    private MultipartFile[] imageFiles;
//    private MultipartFile[] imageFile;
}
