package com.example.numble_insta.service;

import com.example.numble_insta.dto.Message.MessageDto;
import com.example.numble_insta.dto.Message.SendMessageDto;
import com.example.numble_insta.entity.ChatRoom;
import com.example.numble_insta.entity.Message;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.ExistChatRoomException;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.LastPaginationException;
import com.example.numble_insta.exception.NoDataDtoException;
import com.example.numble_insta.repository.ChatRoomRepository;
import com.example.numble_insta.repository.MessageRepository;
import com.example.numble_insta.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;
    public MessageService(UserRepository userRepository, MessageRepository messageRepository, ChatRoomRepository chatRoomRepository, ChatRoomService chatRoomService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatRoomService = chatRoomService;
    }


    //채팅룸이 만들어지고 메세지를 보내야 한다.
    //채팅룸이 없으면
    public void sendMessage(SendMessageDto receiveUser, User user) {
        User sendUser= userRepository.findByUserid(receiveUser.getSend_id());
        ChatRoom chatRoom = chatRoomRepository.findByReceiveid_UseridAndSendid_Userid(receiveUser.getSend_id(), user.getUserid());

        if(chatRoom==null){
            chatRoom = chatRoomService.createChatroom(receiveUser.getSend_id(), user);
        }

        if (!user.isActive() || !sendUser.isActive() || sendUser ==null){
            throw new ExistUserException("존재하지 않는 유저입니다.");
        }
        if(receiveUser.getContent() == null){
            throw new NoDataDtoException("메세지에 내용이 없습니다.");
        }
        Message message = Message.builder()
                .messagecontent(receiveUser.getContent())
                .sendid(user)
                .receiveid(sendUser)
                .senddate(new Date())
                .chatroomid(chatRoom)
                .build();

        messageRepository.save(message);
    }

    public List<MessageDto> getChatRoom(Long chatRoomId, Long userId, User user, Long cursor, Pageable pageable) {
        if (!user.isActive()){
            throw new ExistUserException("존재하지 않는 유저입니다.");
        }

        if(!chatRoomRepository.existsByChatroomid(chatRoomId)){
            throw new ExistChatRoomException("채팅방이 없습니다");
        }
        List<Message> messages = null;
        if(cursor.equals(0L)){
            messages= messageRepository.findByChatroomid_ChatroomidAndReceiveid_UseridOrderByMessageid(chatRoomId,userId,pageable);
        }
        if(!cursor.equals(0L)){
            messages= messageRepository.findByChatroomid_ChatroomidAndReceiveid_UseridAndMessageidGreaterThan(chatRoomId, userId, cursor, pageable);
        }

        if(messages.size()==0){
            throw new LastPaginationException("더 이상 데이터가 없습니다.");
        }

        return messages.stream().map(m ->
                new MessageDto(
                        m.getMessageid(),m.getReceiveid().getUsernickname(),m.getReceiveid().getUserimage(),
                        m.getMessagecontent(), m.getSenddate().toString()
                )).collect(Collectors.toList());


    }


}