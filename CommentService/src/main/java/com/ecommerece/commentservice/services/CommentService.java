package com.ecommerece.commentservice.services;

import com.ecommerece.commentservice.dtos.CommentDto;

import java.util.List;
import java.util.Map;

public interface CommentService {
    CommentDto saveComment(String productId, CommentDto comment);
    List<CommentDto> getAllComments();
    List<CommentDto> getProductComments(String productId);
    List<CommentDto> getUserComments(String userId);
    CommentDto getComment(String commentId);
    void deleteComment(String commentId);
    CommentDto updateComment(String commentId,CommentDto comment);
    CommentDto updateCommentFields(Map<String, Object> fields, String id);


    //TODO: handle null in delete
    //TODO: update
}
