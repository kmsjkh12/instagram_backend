package com.example.numble_insta.controller;

import com.example.numble_insta.dto.User.LoginDto;
import com.example.numble_insta.dto.User.UserDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.service.UserService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserUtil userUtil;

    public UserController(UserService userService, UserUtil userUtil) {
        this.userService = userService;
        this.userUtil = userUtil;
    }


    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@ModelAttribute UserDto userDto){
        try{
            User user = userService.signup(userDto);

            return ResponseEntity.ok(user);
        }
        catch (ExistUserException | NoDataDtoException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
            //예외
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try{
            return ResponseEntity.ok(userService.login(loginDto));
        }
        catch(ExistUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    //회원 탈퇴
    @DeleteMapping("/cancel/{user_id}")
    public ResponseEntity<?> cancel(@PathVariable Long user_id){
        try{
            System.out.print("cancel");
            User user = userUtil.getUtils();
            userService.cancel(user, user_id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (NotEqualsAuthUserException | AlreadyFalseUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@ModelAttribute UserDto userDto){
        try{
            User user = userUtil.getUtils();
            return ResponseEntity.ok(userService.updateProfile(userDto,user));
        }
        catch (AlreadyFalseUserException | NoDataDtoException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }
}
