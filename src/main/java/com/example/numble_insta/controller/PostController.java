package com.example.numble_insta.controller;

import com.example.numble_insta.dto.Post.CreatePostDto;
import com.example.numble_insta.dto.Post.PostDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.service.PostService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
public class PostController {

    private static final Integer PAGE_DEFAULT_SIZE = 1;
    //피드는 한 페이지에 하나씩 보이는 거 같으니 1
    private final PostService postService;
    private final UserUtil userUtil;
    public PostController(PostService postService, UserUtil userUtil) {
        this.postService = postService;
        this.userUtil = userUtil;
    }


    //게시글 생성
    @PostMapping(value = "/createpost")
    public ResponseEntity<?> createPost(@ModelAttribute CreatePostDto createPostDto){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            return ResponseEntity.ok(postService.createPost(createPostDto,user));
        }
        catch (AlreadyFalseUserException | NoDataDtoException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @PutMapping("/updatepost")
    public ResponseEntity<?> updatePost(@ModelAttribute PostDto postDto){
        try{
            User user= userUtil.getUtils();  //아이디 불러오기
            return ResponseEntity.ok(postService.updatePost(postDto,user));
        }
        catch (AlreadyFalseUserException | NoMatchPostUserIdException | NoDataDtoException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @DeleteMapping(value = "/posts/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") Long post_id){
        try{
            User user = userUtil.getUtils();
            postService.deletePost(user,post_id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (NoMatchPostUserIdException | ExistPostException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @GetMapping("/feed/{user_id}/{cursor}")
    public ResponseEntity<?> getFeed(@PathVariable Long user_id , @PathVariable Long cursor){
        try{
            return ResponseEntity.ok(postService.getFeed(user_id,cursor, PageRequest.of(0,PAGE_DEFAULT_SIZE)));
        }
        catch (LastPaginationException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

}
