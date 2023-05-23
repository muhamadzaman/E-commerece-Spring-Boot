package com.ecommerece.userinterface.external.dtos;

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

        private List<GetProductDto> products = new ArrayList<GetProductDto>();

        // Display picture URL from cloudinary will also stored here as a property
    }

