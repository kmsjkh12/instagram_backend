package com.example.numble_insta.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postid;

    @Column
    private String postcontent;

    @Column
    private String postimage;


    //외래키로 작성해야함 다대일
    @ManyToOne
    @JoinColumn(name ="userid")
    private User userid;

}
