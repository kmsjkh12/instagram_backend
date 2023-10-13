package com.example.numble_insta.controller;

import com.example.numble_insta.dto.Comment.CommentDto;
import com.example.numble_insta.dto.Comment.CreateCommentDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.service.CommentService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final UserUtil userUtil;

    private final CommentService commentService;
    public CommentController(UserUtil userUtil, CommentService commentService) {
        this.userUtil = userUtil;
        this.commentService = commentService;
    }

    @PostMapping(value = "/createcomment")
    public ResponseEntity<?> createPost(@ModelAttribute CreateCommentDto createCommentDto){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            return ResponseEntity.ok(commentService.createComment(createCommentDto,user));
        }
        catch (AlreadyFalseUserException | ExistPostException | NoDataDtoException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @PutMapping(value = "/updatecomment")
    public ResponseEntity<?> updateComment(@ModelAttribute CommentDto commentDto){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            return ResponseEntity.ok(commentService.updateComment(commentDto,user));
        }
        catch (AlreadyFalseUserException | NoDataDtoException | NoMatchCommentUserIdException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }
    @DeleteMapping(value ="/comments/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long comment_id){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            commentService.deleteComment(comment_id,user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (NoMatchCommentUserIdException |ExistCommentException |AlreadyFalseUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }
}
