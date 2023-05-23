package com.ecommerece.userservice.controllers;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerece.userservice.dtos.UserDto;
import com.ecommerece.userservice.entities.User;
import com.ecommerece.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("u/users")
public class UserController {
    private final UserService userService;
    private final Cloudinary cloudinary;

    public UserController(UserService userService, Cloudinary cloudinary) {
        this.userService = userService;
        this.cloudinary = cloudinary;
    }

    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        String imagePath = "/home/dev/Downloads/AyubAhmad.png";
//        String imgUrl = cloudinary.url().transformation(new Transformation().width(100).height(150).crop("fill")).generate(imagePath);

        try {
            Map uploadResult = cloudinary.uploader().upload(imagePath, ObjectUtils.emptyMap());
            user.setImageUrl(uploadResult.get("secure_url").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserDto user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    // single user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable String userId) {
        UserDto user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping("{userId}")
    public UserDto updateUser(@PathVariable String userId ,@RequestBody UserDto user){
        return userService.updateUser(userId,user);
    }

    @PatchMapping("{userId}")
    public UserDto updateUserFields(@PathVariable String userId ,@RequestBody Map<String, Object> user){
        return userService.updateUserFields(user ,userId);
    }
}
