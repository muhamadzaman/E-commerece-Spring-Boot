package com.ecommerece.userservice.service;

import com.ecommerece.userservice.dtos.UserDto;
import com.ecommerece.userservice.entities.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto saveUser(User user);
    List<UserDto> getAllUser();
    UserDto getUser(String userId);
    void deleteUser(String userid);
    UserDto updateUser(String userid,UserDto user);

    UserDto updateUserFields(Map<String, Object> fields, String id);

    //TODO: handle null in delete
    //TODO: update
}
