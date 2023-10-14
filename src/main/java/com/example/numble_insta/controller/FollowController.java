package com.example.numble_insta.controller;

import com.example.numble_insta.dto.FollowDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.AreadyFollowException;
import com.example.numble_insta.exception.ExceptionResponse;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.SameFollowerUserException;
import com.example.numble_insta.service.FollowService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FollowController {
    private final FollowService followService;
    private final UserUtil userUtil;

    public FollowController(FollowService followService, UserUtil userUtil) {
        this.followService = followService;
        this.userUtil = userUtil;
    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@ModelAttribute FollowDto followDto){
        try{
            User user =userUtil.getUtils();
            followService.following(followDto, user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (ExistUserException | AreadyFollowException | SameFollowerUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @DeleteMapping("/unfollow/{id}")
    public ResponseEntity<?> unfollow(@PathVariable Long id){
        try{
            User user =userUtil.getUtils();
            followService.unFollow(id, user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (ExistUserException | AreadyFollowException | SameFollowerUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

}
