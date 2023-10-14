package com.example.numble_insta.service;

import com.example.numble_insta.dto.Reply.CreateReplyDto;
import com.example.numble_insta.dto.Reply.ReplyDto;
import com.example.numble_insta.dto.Reply.UpdateReplyDto;
import com.example.numble_insta.entity.Comment;
import com.example.numble_insta.entity.Reply;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.ExistCommentException;
import com.example.numble_insta.exception.ExistReplyException;
import com.example.numble_insta.exception.ExistUserException;
import com.example.numble_insta.exception.NoDataDtoException;
import com.example.numble_insta.repository.CommentRepository;
import com.example.numble_insta.repository.ReplyRepository;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public ReplyService(CommentRepository commentRepository, ReplyRepository replyRepository) {
        this.commentRepository = commentRepository;
        this.replyRepository = replyRepository;
    }

    public ReplyDto createReply(CreateReplyDto createReplyDto, User user) {
        Comment comment =commentRepository.findByCommentid(createReplyDto.getComment_id());

        if(createReplyDto.getContent() ==null){
            throw new NoDataDtoException("답글의 내용이 없습니다.");
        }
        if(comment ==null){
            throw new ExistCommentException("댓글이 없습니다");
        }

        Reply reply = Reply.builder()
                .replycontent(createReplyDto.getContent())
                .commentid(comment)
                .userid(user)
                .build();

        replyRepository.save(reply);

        return ReplyDto.builder()
                .replyid(reply.getReplyid())
                .replycontent(reply.getReplycontent())
                .build();

    }

    public ReplyDto updateReply(UpdateReplyDto updateReplyDto, User user) {
        Reply reply = replyRepository.findByReplyid(updateReplyDto.getReplyid());
        if(updateReplyDto.getContent() == null){
            throw new NoDataDtoException("수정할 답글의 내용이 없습니다.");
        }
        if(!user.isActive()){
            throw new ExistUserException("없는 회원입니다.");
        }

        if(reply ==null){
            throw new ExistReplyException("없는 답글입니다.");
        }
        reply.setReplycontent(updateReplyDto.getContent());

        return ReplyDto.builder()
                .replyid(reply.getReplyid())
                .replycontent(reply.getReplycontent())
                .build();
    }

    public void deleteReply(Long replyId, User user) {
        Reply reply = replyRepository.findByReplyid(replyId);

        if(reply ==null){
            throw new ExistReplyException("없는 답글입니다.");
        }

        if(!user.isActive()){
            throw new ExistUserException("없는 회원입니다.");
        }

        replyRepository.delete(reply);

    }
}
