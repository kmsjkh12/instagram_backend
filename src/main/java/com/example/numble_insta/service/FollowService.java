package com.example.numble_insta.service;

import com.example.numble_insta.dto.FollowDto;
import com.example.numble_insta.entity.Follow;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.AreadyFollowException;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.SameFollowerUserException;
import com.example.numble_insta.repository.FollowRepository;
import com.example.numble_insta.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public FollowService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public void following(FollowDto followDto, User user) {
        User followerUser = userRepository.findByUserid(followDto.getId());
        Follow existsFollow = followRepository.findByFollowerid_UseridAndFollowingid_Userid(followDto.getId(),user.getUserid());
        if(!user.isActive()){
            throw new ExistUserException("탈퇴한 회원입니다.");
        }
        if(!followerUser.isActive()){
            throw new ExistUserException("탈퇴한 회원입니다.");
        }
        if(followerUser.getUserid() == user.getUserid()){
            throw new SameFollowerUserException("자기 자신을 팔로우 할 수 없습니다.");
        }
        if(existsFollow!=null) {
            throw new AreadyFollowException("이미 팔로우한 계정입니다");
        }
        Follow follow = Follow.builder()
                .followingid(user)
                .followerid(followerUser)
                .build();
        followRepository.save(follow);


    }
    public void unFollow(Long id, User user) {
        Follow existsFollow = followRepository.findByFollowerid_UseridAndFollowingid_Userid(id,user.getUserid());
        User followerUser = userRepository.findByUserid(id);

        if(existsFollow==null){
            throw new AreadyFollowException("팔로우가 되어 있지 않습니다.");
        }
        if(!user.isActive()){
            throw new ExistUserException("탈퇴한 회원입니다.");
        }
        if(!followerUser.isActive()){
            throw new ExistUserException("탈퇴한 회원입니다.");
        }

        followRepository.delete(existsFollow);
    }


}
