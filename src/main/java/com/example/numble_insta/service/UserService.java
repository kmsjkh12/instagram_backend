package com.example.numble_insta.service;

import com.example.numble_insta.dto.User.AllUserDto;
import com.example.numble_insta.dto.User.LoginDto;
import com.example.numble_insta.dto.TokenDto;
import com.example.numble_insta.dto.User.UserDto;
import com.example.numble_insta.dto.User.UserFollowDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.AlreadyFalseUserException;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.NoDataDtoException;
import com.example.numble_insta.exception.NotEqualsAuthUserException;
import com.example.numble_insta.jwt.JwtFilter;
import com.example.numble_insta.jwt.TokenProvider;
import com.example.numble_insta.repository.FollowRepository;
import com.example.numble_insta.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final FollowRepository followRepository;
    public UserService(UserRepository userRepository, TokenProvider tokenProvider, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.followRepository = followRepository;
    }


    public AllUserDto signup(UserDto userDto){
        if(userDto.getUsernickname() == null || userDto.getUserimage()== null){
            throw new NoDataDtoException("닉네임, 이미지를 다시 확인해주세요");
        }
        User user =userRepository.findByUsernickname(userDto.getUsernickname());

        if(user != null){
            throw new ExistUserException("이미 있는 닉네임입니다.");
        }
        User saveuser = User.builder()
                .usernickname(userDto.getUsernickname())
                .userimage(userDto.getUserimage().getOriginalFilename())
                .active(true)
                .build();
        userRepository.save(saveuser);

        return AllUserDto.builder()
               .id(saveuser.getUserid())
               .usernickname(saveuser.getUsernickname())
               .userimage(saveuser.getUserimage())
               .build();
    }

    public AllUserDto updateProfile(UserDto userDto, User user) {

        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 계정입니다");
        }
        if(userDto.getUsernickname() == null || userDto.getUserimage() == null){
            throw new NoDataDtoException("닉네임, 이미지를 다시 확인해주세요");
        }
        user.setUsernickname(userDto.getUsernickname());
        user.setUserimage(userDto.getUserimage().getOriginalFilename());

        return AllUserDto.builder()
                .id(user.getUserid())
                .usernickname(user.getUsernickname())
                .userimage(user.getUserimage())
                .build();
    }

    public ResponseEntity<TokenDto> login (LoginDto loginDto){
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER"); //권한
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getNickname(), null , Collections.singleton(simpleGrantedAuthority));
        //CustomUserDetailService 확인!
        User user = userRepository.findByUsernickname(loginDto.getNickname());
        if(user == null){
            throw new ExistUserException("없는 닉네임입니다");
        }
        String jwt = tokenProvider.createToken(authentication); //토큰 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,"Bearer" + jwt);  //헤더에 넣기

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);  //응답
    }

    public void cancel(User updateUser, Long user_id){
        if(updateUser.getUserid() != user_id){
            throw new NotEqualsAuthUserException("인증과 계정 아이디가 일치하지 않습니다.");
        }
        if(!updateUser.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 계정입니다");
        }

        updateUser.setActive(Boolean.FALSE);

    }


    public UserFollowDto selectProfile(Long userId) {
        User user  = userRepository.findByUserid(userId);

        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 회원입니다.");
        }
        Long following = followRepository.countByFollowingid_Userid(userId);
        Long follower = followRepository.countByFollowerid_Userid(userId);

        return UserFollowDto.builder()
                .nickname(user.getUsernickname())
                .profile_image(user.getUserimage())
                .follower(follower)
                .following(following)
                .build();
     }


}
