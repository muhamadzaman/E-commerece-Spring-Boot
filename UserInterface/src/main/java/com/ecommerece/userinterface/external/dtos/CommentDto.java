package com.ecommerece.userinterface.external.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String id;
    @NotBlank(message = "Comment body is mandatory")
    @Size(min = 6, max = 50, message = "Comment length must be minimum 6 and maximum 50 characters")
    private String body;
    private String productId;
    private String userId;

    // Display picture of user URL from cloudinary will also stored here as a property
}

