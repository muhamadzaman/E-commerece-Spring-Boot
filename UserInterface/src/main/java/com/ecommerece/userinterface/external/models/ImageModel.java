package com.ecommerece.userinterface.external.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageModel {

    private Long id;
    private String imageUrl;
    private String productId;

}
