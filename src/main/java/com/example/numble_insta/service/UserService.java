package com.example.numble_insta.service;

import com.example.numble_insta.dto.UserDto;
import com.example.numble_insta.entity.UserEntity;
import com.example.numble_insta.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity signup(UserDto userDto){
        if(userDto.getUser_image() == null || userDto.getUser_image()== null){
            //예외처리(비어있다)
        }
        UserEntity user =userRepository.findByUser_nickname(userDto.getUser_nickname());

        if(user != null){
            //에외처리 이미 있는 닉네임
        }
        UserEntity saveuser = UserEntity.builder()
                .user_nickname(userDto.getUser_nickname())
                .user_image(userDto.getUser_image())
                .user_active(true)
                .build();
       return userRepository.save(saveuser);
    }
}
