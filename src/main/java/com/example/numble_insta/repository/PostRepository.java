package com.example.numble_insta.repository;

import com.example.numble_insta.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    Post findByPostid(Long id);
}
