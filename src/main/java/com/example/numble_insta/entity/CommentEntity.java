package com.example.numble_insta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column
    private String comment_content;

    @Column
    private Long post_id; //댓글을 작성한 글 번호 다대일

    @Column
    private Long writing_user_id;  //작성자  다대일
}
