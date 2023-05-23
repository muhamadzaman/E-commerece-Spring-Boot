package com.ecommerece.commentservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

}
