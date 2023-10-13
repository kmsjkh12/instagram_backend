package com.example.numble_insta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyid;

    @Column
    private String replycontent;

    @Column
    private Long commentid;  //댓글 번호
    @Column
    private Long userid;    //댓글 작성자

}
