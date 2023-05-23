package com.ecommerece.userservice.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Column(unique = true)
    private String id;
    @NotBlank(message = "Product name is mandatory")
    @Size(min = 6, max = 20, message = "Name length must be between 6 and 20 characters")
    private String productName;
    @NotBlank(message = "Product description is mandatory")
    @Size(min = 20, message = "Description length must be minimum 20 characters")
    private String description;
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 50000, message = "Quantity must be at max 50000")
    private int availableQuantity;
    @Min(value = 1, message = "Price must be at least 1")
    @Max(value = 1000000, message = "Price must be at maximum 1000000")
    private BigDecimal price;
    private String userId;
    private List<CommentDto> comments = new ArrayList<CommentDto>();

    // Display picture URL from cloudinary will also stored here as a property
}

