package com.example.numble_insta.dto.Message;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListChatRoomDto {
    private Long id;
    private String niname;
    private String image;
    private String lastMessage;
    private String lastDate;
}
