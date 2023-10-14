package com.example.numble_insta.controller;

import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.ExceptionResponse;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.LastPaginationException;
import com.example.numble_insta.service.ChatRoomService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatRoomController {
    private static final Integer PAGE_DEFAULT_SIZE = 11;

    private final UserUtil userUtil;
    private final ChatRoomService chatRoomService;
    public ChatRoomController(UserUtil userUtil, ChatRoomService chatRoomService) {
        this.userUtil = userUtil;
        this.chatRoomService = chatRoomService;
    }


    @GetMapping(value = "/listchat/{user_id}/{cursor}")
    public ResponseEntity<?> chatList(@PathVariable Long user_id, @PathVariable Long cursor){

        try {
            User user = userUtil.getUtils();
            return ResponseEntity.ok(chatRoomService.listChatRoom(user_id, user, cursor, PageRequest.of(0, PAGE_DEFAULT_SIZE)));
        }
        catch(ExistUserException | LastPaginationException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }

    }



}
