package com.example.numble_insta.repository;

import com.example.numble_insta.entity.Comment;
import com.example.numble_insta.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    Post findByPostid(Long id);
    List<Post> findByUserid_UseridOrderByPostid(Long userid, Pageable pageable);
    List<Post> findByUserid_UseridAndPostidGreaterThan(Long userid,Long postid, Pageable pageable);

    //user id로 검색하고 두 번째 검색 조건으로, postid가 특정 값보다 큰 게시물을 검색합니다. postid 파라미터로 줌
}
