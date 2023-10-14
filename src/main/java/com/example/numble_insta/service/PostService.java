package com.example.numble_insta.service;

import com.example.numble_insta.dto.Comment.FeedCommentDto;
import com.example.numble_insta.dto.FeedDto;
import com.example.numble_insta.dto.Post.CreatePostDto;
import com.example.numble_insta.dto.Post.PostDto;
import com.example.numble_insta.dto.Reply.FeedReplyDto;
import com.example.numble_insta.entity.Post;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.*;
import com.example.numble_insta.repository.CommentRepository;
import com.example.numble_insta.repository.PostRepository;
import com.example.numble_insta.repository.ReplyRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final ReplyRepository replyRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.replyRepository = replyRepository;
    }

    public PostDto createPost(CreatePostDto createPostDto, User user){

        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 회원입니다.");
        }
        if(createPostDto.getPostcontent() ==null || createPostDto.getPostimage() == null){
            throw new NoDataDtoException("게시글의 내용, 이미지가 비어있습니다.");
        }

        Post post = Post.builder()
                .postcontent(createPostDto.getPostcontent())
                .postimage(createPostDto.getPostimage().getOriginalFilename())
                .userid(user)
                .build();
        postRepository.save(post);

        return PostDto.builder()
                .postid(post.getPostid())
                .postcontent(post.getPostcontent())
                .build();
    }

    public PostDto updatePost(PostDto postDto, User user) {
        Post post = postRepository.findByPostid(postDto.getPostid());

        if(!user.isActive()){
            throw new AlreadyFalseUserException("이미 탈퇴한 회원입니다.");
        }
        System.out.print(post.getUserid().getUserid());
        System.out.print(user.getUserid());
        if(post.getUserid().getUserid() != user.getUserid()){
            throw new NoMatchPostUserIdException("로그인 된 계정과 게시글을 수정하는 사람의 아이디가 일치하지 않습니다.");
        }

        if(postDto.getPostcontent() == null || postDto.getPostimage() == null){
            throw new NoDataDtoException("게시글의 내용, 이미지가 비어있습니다.");
        }

        post.setPostcontent(postDto.getPostcontent());
        post.setPostimage(postDto.getPostimage().getOriginalFilename());

        post.setUserid(null);
        return PostDto.builder()
                .postid(post.getPostid())
                .postcontent(post.getPostcontent())
                .build();


    }

    public void deletePost(User user, Long postid) {
        Post post = postRepository.findByPostid(postid);

        if(post ==null){
            throw new ExistPostException("게시글이 없습니다");
        }
        if(post.getUserid().getUserid() != user.getUserid()){
            throw new NoMatchPostUserIdException("로그인 된 계정과 사용자가 일치하지 않습니다.");
        }

        postRepository.delete(post);
    }

    public List<FeedDto> getFeed(Long user_id, Long cursor, Pageable pageable) {
        List<Post> post = null;
        if(cursor.equals(0L)){
            post = postRepository.findByUserid_UseridOrderByPostid(user_id,pageable);
        }
       if(!cursor.equals(0L)) {
           post = postRepository.findByUserid_UseridAndPostidGreaterThan(user_id, cursor, pageable);
       }
        //포스트를 가져와서 포스트에 해당하는 댓글과 대댓글을 넣어줄예정
        if (post.size()==0) {
            throw new LastPaginationException("더 이상 페이지가 없습니다.");
        }



        //다른 방법이 잘떠오르지 않아 이런 방식으로 함
        List<FeedDto> feed = post.stream().map(
                p-> new FeedDto(p.getPostid(),p.getPostcontent(),p.getPostimage(),
                        commentRepository.findByPostid_Postid(p.getPostid())
                                .stream().map(
                                        c->
                                                new FeedCommentDto(
                                                        c.getCommentid(), c.getCommentcontent(), c.getUserid().getUsernickname(),
                                                        c.getUserid().getUserimage(),
                                                        replyRepository.findByCommentid_Commentid(c.getCommentid())
                                                                .stream().map(
                                                                        r-> new FeedReplyDto(
                                                                                r.getReplyid(), r.getReplycontent(),
                                                                                r.getUserid().getUsernickname(),
                                                                                r.getUserid().getUserimage()
                                                                        )
                                                                ).collect(Collectors.toList()))
                                ).collect(Collectors.toList())
                        )
        ).collect(Collectors.toList());
return feed;
    }


}
