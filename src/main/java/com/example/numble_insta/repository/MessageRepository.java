package com.example.numble_insta.repository;

import com.example.numble_insta.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findTopByChatroomid_ChatroomidOrderBySenddateDesc(Long chatroomid);
    List<Message> findByChatroomid_ChatroomidAndReceiveid_Userid(Long chatroomid , Long receiveid);

    List<Message> findByChatroomid_ChatroomidAndReceiveid_UseridOrderByMessageid(Long chatroomid , Long receiveid, Pageable pageable);

    List<Message> findByChatroomid_ChatroomidAndReceiveid_UseridAndMessageidGreaterThan(Long chatroomid , Long receiveid, Long messageid, Pageable pageable);

}
