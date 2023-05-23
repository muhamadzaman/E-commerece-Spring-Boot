package com.ecommerece.productservice.entities;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    private String id;
    private String productName;
    private String description;
    private int availableQuantity;
    private BigDecimal price;
    private String userId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ImageModel> images;

    // Display pictures URL from cloudinary will also stored here as a property
}
