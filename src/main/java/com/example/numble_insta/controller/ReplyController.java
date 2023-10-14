package com.example.numble_insta.controller;

import com.example.numble_insta.dto.Reply.CreateReplyDto;
import com.example.numble_insta.dto.Reply.UpdateReplyDto;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.service.ReplyService;
import com.example.numble_insta.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReplyController {
    private final UserUtil userUtil;

    private final ReplyService replyService;

    public ReplyController(UserUtil userUtil, ReplyService replyService) {
        this.userUtil = userUtil;
        this.replyService = replyService;
    }

    @PostMapping(value = "/createreply")
    public ResponseEntity<?> createReply (@ModelAttribute CreateReplyDto createReplyDto){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            return ResponseEntity.ok(replyService.createReply(createReplyDto,user));
        }
        catch (ExistCommentException | NoDataDtoException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @PutMapping(value = "/updatereply")
    public ResponseEntity<?> updateReply(@ModelAttribute UpdateReplyDto UpdateReplyDto){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            return ResponseEntity.ok(replyService.updateReply(UpdateReplyDto,user));
        }
        catch ( ExistReplyException|ExistUserException|NoDataDtoException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }

    @DeleteMapping(value ="/reply/{reply_id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long reply_id){
        try{
            User user = userUtil.getUtils();  //아이디 불러오기
            replyService.deleteReply(reply_id,user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (NoMatchCommentUserIdException | ExistCommentException | AlreadyFalseUserException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptionResponse);
        }
    }
}
