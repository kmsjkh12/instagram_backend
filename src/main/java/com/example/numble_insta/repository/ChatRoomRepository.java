package com.example.numble_insta.repository;

import com.example.numble_insta.entity.ChatRoom;
import com.example.numble_insta.entity.Follow;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByReceiveid_UseridAndSendid_Userid(Long receiveid, Long sendid);
    List<ChatRoom> findBySendid_UseridOrderByChatroomid(Long userid, Pageable pageable);
    List<ChatRoom> findBySendid_UseridAndChatroomidGreaterThan(Long userid,Long chatroomid, Pageable pageable);
    boolean existsByChatroomid(Long id);
}
