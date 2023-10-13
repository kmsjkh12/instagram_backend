package com.example.numble_insta.service;

import com.example.numble_insta.dto.Post.CreatePostDto;
import com.example.numble_insta.dto.Post.PostDto;
import com.example.numble_insta.entity.Post;
import com.example.numble_insta.entity.User;
import com.example.numble_insta.exception.AlreadyFalseUserException;
import com.example.numble_insta.exception.ExistPostException;
import com.example.numble_insta.exception.NoDataDtoException;
import com.example.numble_insta.exception.NoMatchPostUserIdException;
import com.example.numble_insta.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(CreatePostDto createPostDto, User user){

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

        return post;
    }

    public Post updatePost(PostDto postDto, User user) {
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
        return post;


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
}
