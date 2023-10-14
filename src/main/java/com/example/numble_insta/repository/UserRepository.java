package com.example.numble_insta.repository;

import com.example.numble_insta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(Long id);
    User findByUsernickname(String nickname);
}
