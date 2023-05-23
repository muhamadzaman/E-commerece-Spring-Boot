package com.ecommerece.productservice.dtos;

import lombok.*;

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

    // Display picture URL from cloudinary will also stored here as a property
}

