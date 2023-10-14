package com.example.numble_insta.service;

import com.example.numble_insta.dto.Message.ListChatRoomDto;
import com.example.numble_insta.entity.ChatRoom;
import com.example.numble_insta.entity.Message;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.LastPaginationException;
import com.example.numble_insta.repository.ChatRoomRepository;
import com.example.numble_insta.repository.MessageRepository;
import com.example.numble_insta.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public ChatRoom createChatroom (Long receive_id , User user){
        User receiveUser= userRepository.findByUserid(receive_id);

        if(!receiveUser.isActive() || !user.isActive()) {
            throw new ExistUserException("존재하지 않는 회원입니다.");
        }
        ChatRoom chatRoom = ChatRoom.builder()
                .sendid(user)
                .receiveid(receiveUser)
                .build();

        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }




    //채팅룸 페이지네이션 11개씩
    public List<ListChatRoomDto> listChatRoom(Long user_id, User user,Long cursor, Pageable pageable) {

        if(!user.isActive()){
            throw new ExistUserException("탈퇴한 회원입니다.");

        }
        if(user_id != user.getUserid()){
            throw new ExistUserException("조회하는 아디와 로그인 아이디가 일치하지 않습니다.");
        }
        List<ChatRoom> chatRoomsList = null ;

        if(cursor.equals(0L)){
            chatRoomsList = chatRoomRepository.findBySendid_UseridOrderByChatroomid(user.getUserid(),pageable);
        }
        if(!cursor.equals(0L)){
            chatRoomsList = chatRoomRepository.findBySendid_UseridAndChatroomidGreaterThan(user.getUserid(),cursor,pageable);
        }
        if(chatRoomsList.size() ==0){
            throw new LastPaginationException("더이상 데이터가 없습니다.");
        }
        return chatRoomsList.stream().map(c->
        {
            Message latestMessage = messageRepository.findTopByChatroomid_ChatroomidOrderBySenddateDesc(c.getChatroomid());

            return new ListChatRoomDto(
                    c.getChatroomid(),
                    c.getReceiveid().getUserimage(),
                    c.getReceiveid().getUserimage(),
                    latestMessage.getMessagecontent(),
                    latestMessage.getSenddate().toString()
            );
        }).collect(Collectors.toList());
    }


}
