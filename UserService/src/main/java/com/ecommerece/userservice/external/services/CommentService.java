package com.ecommerece.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("COMMENT-SERVICE")
public interface CommentService {

}
