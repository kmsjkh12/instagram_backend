package com.example.numble_insta.controller;

import com.example.numble_insta.dto.UserDto;
import com.example.numble_insta.entity.UserEntity;
import com.example.numble_insta.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    // 요청
    // nickname	    string              유저 닉네임
    // profile_image	multipartFile	    프로필 사진

    // 응답
    // id              long                유저 id  //자동 생성

    // nickname	    string              유저 닉네임
    // profile_image	multipartFile	    프로필 사진 url


    public ResponseEntity<?> signUp(@ModelAttribute UserDto userDto){
        try{
            UserEntity user = userService.signup(userDto);
            return ResponseEntity.ok(user);
        }
        catch () {
            //서비스에서 던진 예외 두개를 넣고

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body() //예외
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(){
        try{
    }
}
