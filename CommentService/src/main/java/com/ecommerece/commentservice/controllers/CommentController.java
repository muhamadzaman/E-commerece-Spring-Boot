package com.ecommerece.commentservice.controllers;

import com.ecommerece.commentservice.dtos.CommentDto;
import com.ecommerece.commentservice.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("c")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/products/{productId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable String productId, @Valid @RequestBody CommentDto comment) {
        CommentDto comment1 = commentService.saveComment(productId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment1);
    }
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable String commentId) {
        CommentDto comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<CommentDto>> getUserComments(@PathVariable String userId) {
        List<CommentDto> userComments = commentService.getUserComments(userId);
        return ResponseEntity.ok(userComments);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<CommentDto>> getProductComments(@PathVariable String productId) {
        List<CommentDto> productComments = commentService.getProductComments(productId);
        return ResponseEntity.ok(productComments);
    }

    @PutMapping("/comments/{commentId}")
    public CommentDto updateProduct(@PathVariable String commentId ,@RequestBody CommentDto comment){
        return commentService.updateComment(commentId,comment);
    }

    @PatchMapping("/comments/{commentId}")
    public CommentDto updateProductFields(@PathVariable String commentId ,@RequestBody Map<String, Object> comment){
        return commentService.updateCommentFields(comment ,commentId);
    }
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable String commentId){
        commentService.deleteComment(commentId);
    }

}
