package com.example.numble_insta.controller;

import com.example.numble_insta.dto.Message.SendMessageDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.service.MessageService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    private static final Integer PAGE_DEFAULT_SIZE = 10;
    //메세지는 한번에 10개가 보이는듯
    private final UserUtil userUtil;
    private final MessageService messageService;


    public MessageController(UserUtil userUtil, MessageService messageService) {
        this.userUtil = userUtil;
        this.messageService = messageService;
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<?> sendMessage(@ModelAttribute SendMessageDto sendMessageDto){
        try{
            User user= userUtil.getUtils();
            messageService.sendMessage(sendMessageDto, user);
            return ResponseEntity.ok(HttpStatus.OK);

        }
        catch(NoDataDtoException | ExistUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @GetMapping("/messagelist/{chat_room_id}/{user_id}/{cursor}")
    public ResponseEntity<?> getChatRoom(@PathVariable Long chat_room_id,@PathVariable Long user_id, @PathVariable Long cursor){
        try{
            User user= userUtil.getUtils();
            return ResponseEntity.ok(messageService.getChatRoom(chat_room_id, user_id,user, cursor, PageRequest.of(0,PAGE_DEFAULT_SIZE)));
        }
        catch (ExistChatRoomException | ExistUserException | LastPaginationException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }
}
