package com.example.numble_insta.dto.Message;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Long id;
    private String nickname;
    private String image;
    private String content;
    private String date;

}
