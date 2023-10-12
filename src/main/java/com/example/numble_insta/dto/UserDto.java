package com.example.numble_insta.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class UserDto {

    private Long user_id;


    private String user_nickname;

    private String user_image;

    private boolean user_active;


    @Builder
    public UserDto(Long user_id, String user_nickname, String user_image, boolean user_active){
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.user_active = user_active;
    }

}
