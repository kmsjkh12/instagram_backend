package com.example.numble_insta.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;

    @Column
    private String commentcontent;

    @ManyToOne
    @JoinColumn(name ="postid")
    private Post postid; //댓글을 작성한 글 번호 다대일

    @ManyToOne
    @JoinColumn(name ="userid")
    private User userid;  //작성자  다대일
}
