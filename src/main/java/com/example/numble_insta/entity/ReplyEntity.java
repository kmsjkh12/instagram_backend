package com.example.numble_insta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reply_id;

    @Column
    private String reply_content;

    @Column
    private Long comment_id;  //댓글 번호
    @Column
    private Long user_id;    //댓글 작성자

}
