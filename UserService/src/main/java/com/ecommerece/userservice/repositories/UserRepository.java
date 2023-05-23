package com.ecommerece.userservice.repositories;

import com.ecommerece.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
