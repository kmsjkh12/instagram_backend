# instagram_backend
넘블 챌린지 인스타 백엔드서비스 개발


## 가이드라인
https://thoughtful-arch-8c2.notion.site/Spring-c83f01ab221a4166a2713120728aa552


## 작업기간
2023_10-12 ~ 
## 가이드라인 앱 이미지

<img width="1146" alt="스크린샷 2023-10-14 오후 7 59 40" src="https://github.com/kmsjkh12/instagram_backend/assets/121033327/cf7f6115-9f22-46ff-b44b-e6341c858c63">

## 데이터베이스 ERD
<img width="947" alt="스크린샷 2023-10-14 오후 7 54 56" src="https://github.com/kmsjkh12/instagram_backend/assets/121033327/43cc0969-593b-4ba5-a49a-ef3020e76799">

## 사용기술
Spring Boot <br/>
Gradle<br/>
Java 11<br/>
Spring Data JPA<br/>
MySQL<br/>
JWT<br/>
Spring Security

## 폴더 구조
<pre>
│   │   │   └── com
│   │   │       └── example
│   │   │           └── numble_insta
│   │   │               ├── NumbleInstaApplication.class
│   │   │               ├── config
│   │   │               │   ├── SecurityConfig.class
│   │   │               │   └── SwaggerConfig.class
│   │   │               ├── controller
│   │   │               │   ├── ChatRoomController.class
│   │   │               │   ├── CommentController.class
│   │   │               │   ├── FollowController.class
│   │   │               │   ├── MessageController.class
│   │   │               │   ├── PostController.class
│   │   │               │   ├── ReplyController.class
│   │   │               │   └── UserController.class
│   │   │               ├── dto
│   │   │               │   ├── Comment
│   │   │               │   │   ├── CommentDto$CommentDtoBuilder.class
│   │   │               │   │   ├── CommentDto.class
│   │   │               │   │   ├── CreateCommentDto$CreateCommentDtoBuilder.class
│   │   │               │   │   ├── CreateCommentDto.class
│   │   │               │   │   ├── FeedCommentDto$FeedCommentDtoBuilder.class
│   │   │               │   │   ├── FeedCommentDto.class
│   │   │               │   │   └── UpdateCommentDto.class
│   │   │               │   ├── FeedDto$FeedDtoBuilder.class
│   │   │               │   ├── FeedDto.class
│   │   │               │   ├── FollowDto$FollowDtoBuilder.class
│   │   │               │   ├── FollowDto.class
│   │   │               │   ├── Message
│   │   │               │   │   ├── ChatRoomDto$ChatRoomDtoBuilder.class
│   │   │               │   │   ├── ChatRoomDto.class
│   │   │               │   │   ├── CreateChatRommDto.class
│   │   │               │   │   ├── ListChatRoomDto$ListChatRoomDtoBuilder.class
│   │   │               │   │   ├── ListChatRoomDto.class
│   │   │               │   │   ├── MessageDto$MessageDtoBuilder.class
│   │   │               │   │   ├── MessageDto.class
│   │   │               │   │   ├── SendMessageDto$SendMessageDtoBuilder.class
│   │   │               │   │   └── SendMessageDto.class
│   │   │               │   ├── Post
│   │   │               │   │   ├── CreatePostDto$CreatePostDtoBuilder.class
│   │   │               │   │   ├── CreatePostDto.class
│   │   │               │   │   ├── PostDto$PostDtoBuilder.class
│   │   │               │   │   └── PostDto.class
│   │   │               │   ├── Reply
│   │   │               │   │   ├── CreateReplyDto$CreateReplyDtoBuilder.class
│   │   │               │   │   ├── CreateReplyDto.class
│   │   │               │   │   ├── FeedReplyDto$FeedReplyDtoBuilder.class
│   │   │               │   │   ├── FeedReplyDto.class
│   │   │               │   │   ├── ReplyDto$ReplyDtoBuilder.class
│   │   │               │   │   ├── ReplyDto.class
│   │   │               │   │   ├── UpdateReplyDto$UpdateReplyDtoBuilder.class
│   │   │               │   │   └── UpdateReplyDto.class
│   │   │               │   ├── TokenDto$TokenDtoBuilder.class
│   │   │               │   ├── TokenDto.class
│   │   │               │   └── User
│   │   │               │       ├── AllUserDto$AllUserDtoBuilder.class
│   │   │               │       ├── AllUserDto.class
│   │   │               │       ├── LoginDto$LoginDtoBuilder.class
│   │   │               │       ├── LoginDto.class
│   │   │               │       ├── UserDto$UserDtoBuilder.class
│   │   │               │       ├── UserDto.class
│   │   │               │       ├── UserFollowDto$UserFollowDtoBuilder.class
│   │   │               │       └── UserFollowDto.class
│   │   │               ├── entity
│   │   │               │   ├── Authority$AuthorityBuilder.class
│   │   │               │   ├── Authority.class
│   │   │               │   ├── ChatRoom$ChatRoomBuilder.class
│   │   │               │   ├── ChatRoom.class
│   │   │               │   ├── Comment$CommentBuilder.class
│   │   │               │   ├── Comment.class
│   │   │               │   ├── Follow$FollowBuilder.class
│   │   │               │   ├── Follow.class
│   │   │               │   ├── Message$MessageBuilder.class
│   │   │               │   ├── Message.class
│   │   │               │   ├── Post$PostBuilder.class
│   │   │               │   ├── Post.class
│   │   │               │   ├── Reply$ReplyBuilder.class
│   │   │               │   ├── Reply.class
│   │   │               │   ├── User$UserBuilder.class
│   │   │               │   └── User.class
│   │   │               ├── exception
│   │   │               │   ├── AlreadyFalseUserException.class
│   │   │               │   ├── AreadyFollowException.class
│   │   │               │   ├── ExceptionResponse.class
│   │   │               │   ├── ExistChatRoomException.class
│   │   │               │   ├── ExistCommentException.class
│   │   │               │   ├── ExistPostException.class
│   │   │               │   ├── ExistReplyException.class
│   │   │               │   ├── ExistUserException.class
│   │   │               │   ├── LastPaginationException.class
│   │   │               │   ├── NoDataDtoException.class
│   │   │               │   ├── NoMatchCommentUserIdException.class
│   │   │               │   ├── NoMatchPostUserIdException.class
│   │   │               │   ├── NotEqualsAuthUserException.class
│   │   │               │   ├── NotEqualsMessageException.class
│   │   │               │   └── SameFollowerUserException.class
│   │   │               ├── jwt
│   │   │               │   ├── JwtAccessDeniedHandler.class
│   │   │               │   ├── JwtAuthenticationEntryPoint.class
│   │   │               │   ├── JwtFilter.class
│   │   │               │   ├── JwtSecurityConfig.class
│   │   │               │   └── TokenProvider.class
│   │   │               ├── repository
│   │   │               │   ├── ChatRoomRepository.class
│   │   │               │   ├── CommentRepository.class
│   │   │               │   ├── FollowRepository.class
│   │   │               │   ├── MessageRepository.class
│   │   │               │   ├── PostRepository.class
│   │   │               │   ├── ReplyRepository.class
│   │   │               │   └── UserRepository.class
│   │   │               ├── service
│   │   │               │   ├── ChatRoomService.class
│   │   │               │   ├── CommentService.class
│   │   │               │   ├── CustomUserDetailsService.class
│   │   │               │   ├── FollowService.class
│   │   │               │   ├── MessageService.class
│   │   │               │   ├── PostService.class
│   │   │               │   ├── ReplyService.class
│   │   │               │   └── UserService.class
│   │   │               └── util
│   │   │                   └── UserUtil.class

</pre>


## 기억에 남는 코드
게시글 리스트에 게시글에 맞는 댓글과 덧글을 넣어서 출력하는 코드
  하드 코딩이 된 거 같지만 다른 방법이 생각이 안나서 이런 식으로 구현함
<pre>
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
</pre>
## 커서 기반 페이지 네이션
JPA에 GreaterThan을 활용해서 구현함, 사용자가 본 마지막 postid를 커서로 받아서 페이지네이션 구현
 <pre>   
   예시) List<Post> findByUserid_UseridAndPostidGreaterThan(Long userid,Long postid, Pageable pageable);
</pre>

## 보완할 점
access token, refresh token, 트러블 슈팅 내용 정리, 테스트 코드와 배포는 추후 진행 예정


## 후기 
1. 전체적인 스프링, 자바에 대한 이해도가 올라간 것 같고 JPA에 대한 공부가 잘된 것 같습니다. <br/>
2. 스프링 시큐리티와, JWT 로그인 방식에 대한 개념이 부족했는데 이번 공부를 통해 이해도가 올라간 것 같습니다. <br/>
3. 커서로 페이지네이션을 처음 해봤는데 좋은 경험이 된 것 같습니다.
