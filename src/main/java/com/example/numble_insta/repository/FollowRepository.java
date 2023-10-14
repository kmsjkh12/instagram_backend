package com.example.numble_insta.repository;

import com.example.numble_insta.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByFollowerid_UseridAndFollowingid_Userid(Long followerid, Long followingid);

    Long countByFollowerid_Userid(Long id);
    Long countByFollowingid_Userid(Long id);

}
