package com.example.numble_insta.repository;

import com.example.numble_insta.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByCommentid(Long id);
}
