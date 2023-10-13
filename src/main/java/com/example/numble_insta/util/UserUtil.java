package com.example.numble_insta.util;

import com.example.numble_insta.entity.User;
import com.example.numble_insta.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    private final UserRepository userRepository;

    public UserUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUtils(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Authentication 객체는 사용자의 인증 및 권한 정보
        String nickname = authentication.getName();
        return userRepository.findByUsernickname(nickname);
    }

    //유저 반환해주는 util!
}
