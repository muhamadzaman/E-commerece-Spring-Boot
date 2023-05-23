package com.ecommerece.commentservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    private String id;
    private String body;
    private String productId;
    private String userId;

    // Display pictures URL from cloudinary will also stored here as a property
}
