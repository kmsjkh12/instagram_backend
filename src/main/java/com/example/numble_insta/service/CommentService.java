package com.example.numble_insta.service;

import com.example.numble_insta.dto.Comment.CommentDto;
import com.example.numble_insta.dto.Comment.CreateCommentDto;
import com.example.numble_insta.entity.Comment;
import com.example.numble_insta.entity.Post;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.repository.CommentRepository;
import com.example.numble_insta.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;
    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDto createComment(CreateCommentDto createCommentDto, User user) {
        Post post = postRepository.findByPostid(createCommentDto.getPostid());
        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 회원입니다.");
        }

        if(createCommentDto.getContent() ==null) {
            throw new NoDataDtoException("댓글의 내용이비어있습니다.");
        }

        if(post ==null){
            throw new ExistPostException("게시글이 존재하지않습니다.");
        }

        Comment comment = Comment.builder()
                .commentcontent(createCommentDto.getContent())
                .postid(post)
                .userid(user)
                .build();
        commentRepository.save(comment);
        comment.setPostid(null);
        comment.setUserid(null);
        return CommentDto.builder()
                .id(comment.getCommentid())
                .content(comment.getCommentcontent())
                .build();
    }

    public CommentDto updateComment(CommentDto commentDto, User user){
        Comment comment = commentRepository.findByCommentid(commentDto.getId());

        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 회원입니다.");
        }
        if(commentDto.getContent() ==null) {
            throw new NoDataDtoException("수정할 댓글이 비어있습니다.");
        }

        if(comment.getUserid().getUserid() != user.getUserid()){
            throw new NoMatchCommentUserIdException("일치하지않는 계정입니다");
        }
        comment.setCommentcontent(commentDto.getContent());
        return CommentDto.builder()
                .id(comment.getCommentid())
                .content(comment.getCommentcontent())
                .build();
    }
    public void deleteComment(Long id, User user){
        Comment comment = commentRepository.findByCommentid(id);
        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 회원입니다.");
        }
        if(comment == null){
            throw new ExistCommentException("없는 댓글입니다");
        }
        if(comment.getUserid().getUserid() != user.getUserid()){
            throw new NoMatchCommentUserIdException("일치하지않는 계정입니다");
        }
        commentRepository.delete(comment);
    }
}
