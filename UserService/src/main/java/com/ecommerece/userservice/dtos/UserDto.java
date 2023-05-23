package com.ecommerece.userservice.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class UserDto {
        private String id;
        private String username;
        private String email;
        private String password;
        private String contact;

        private String imageUrl;

        private List<ProductDto> products = new ArrayList<ProductDto>();

        // Display picture URL from cloudinary will also stored here as a property
    }

