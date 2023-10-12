package com.example.numble_insta.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String user_nickname;

    @Column
    private String user_image;

    @Column
    private boolean user_active;


    @Builder
    public UserEntity(Long user_id, String user_nickname, String user_image, boolean user_active){
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.user_active = user_active;
    }

}
