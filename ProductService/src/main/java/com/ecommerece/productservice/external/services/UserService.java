package com.ecommerece.productservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

}
