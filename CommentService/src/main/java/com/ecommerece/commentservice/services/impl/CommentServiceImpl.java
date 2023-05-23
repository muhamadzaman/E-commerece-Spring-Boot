package com.ecommerece.commentservice.services.impl;

import com.ecommerece.commentservice.dtos.CommentDto;
import com.ecommerece.commentservice.entities.Comment;
import com.ecommerece.commentservice.exceptions.ResourceNotFoundException;
import com.ecommerece.commentservice.repositories.CommentRepository;
import com.ecommerece.commentservice.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto saveComment(String productId, CommentDto comment) {
        //generate  unique userid
        String randomCommentId = UUID.randomUUID().toString();
        comment.setId(randomCommentId);
        comment.setProductId((productId));
        // This 2 line code will be removed after getting user from authentication.
        String randomUserId = UUID.randomUUID().toString();
        comment.setUserId(randomUserId);
        return getCommentDtofromComment(commentRepository.save(getCommentfromCommentDto(comment)));
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream().map(this::getCommentDtofromComment).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getProductComments(String productId) {
        return commentRepository.findAllByProductId(productId).stream().map(this::getCommentDtofromComment).collect(Collectors.toList());
    }
//
//    @Override
    public List<CommentDto> getUserComments(String userId) {
        return commentRepository.findAllByUserId(userId).stream().map(this::getCommentDtofromComment).collect(Collectors.toList());
    }

    @Override
    public CommentDto getComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment with given id is not found on server !! : " + commentId));
        return getCommentDtofromComment(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto updateComment(String commentId, CommentDto comment) {
        comment.setId(commentId);
        return getCommentDtofromComment(commentRepository.save(getCommentfromCommentDto(comment)));
    }

    @Override
    public CommentDto updateCommentFields(Map<String, Object> fields, String id) {
        CommentDto currentComment = getComment(id);
        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(CommentDto.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,currentComment,value);
        });
        return getCommentDtofromComment(commentRepository.save(getCommentfromCommentDto(currentComment)));
    }
    private CommentDto getCommentDtofromComment(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment getCommentfromCommentDto(CommentDto comment){
        return modelMapper.map(comment, Comment.class);
    }
}
