package com.example.numble_insta.repository;

import com.example.numble_insta.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply findByReplyid(Long id);
    List<Reply> findByCommentid_Commentid(Long id);

}
