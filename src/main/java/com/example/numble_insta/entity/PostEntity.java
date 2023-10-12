package com.example.numble_insta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column
    private String post_content;

    @Column
    private String post_image;


    //외래키로 작성해야함 다대일
    @Column
    private Long user_id;
}
