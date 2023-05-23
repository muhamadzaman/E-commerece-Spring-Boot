package com.ecommerece.commentservice.repositories;

import com.ecommerece.commentservice.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findAllByUserId(String userId);
    List<Comment> findAllByProductId(String productId);
}
