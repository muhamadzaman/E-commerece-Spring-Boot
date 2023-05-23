package com.ecommerece.userinterface.external.dtos;

import com.ecommerece.userinterface.external.models.ImageModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductDto {
    private String id;
    private String productName;
    private String description;
    private int availableQuantity;
    private BigDecimal price;
    private String userId;
    private List<CommentDto> comments = new ArrayList<>();
    private List<ImageModel> images = new ArrayList<>();

    // Display picture URL from cloudinary will also stored here as a property
}

