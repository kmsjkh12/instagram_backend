package com.example.numble_insta.service;

import com.example.numble_insta.entity.User;
import com.example.numble_insta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component("userDetailService")
@RequiredArgsConstructor

public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User existUser = userRepository.findByUsernickname(nickname);
        if(existUser==null){
            throw new UsernameNotFoundException("데이터베이스에서 찾을 수 없다");
        }
        return createUser(nickname,existUser);
    }

    private org.springframework.security.core.userdetails.User createUser(String nickname, User user) {
        if(!user.isActive()){
            throw new RuntimeException(nickname + " ->활성화되어 있지 않습니다");
        }
        return new org.springframework.security.core.userdetails.User(nickname, null ,new ArrayList<>());
    }
}
