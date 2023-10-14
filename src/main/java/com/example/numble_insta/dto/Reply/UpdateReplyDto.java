package com.example.numble_insta.dto.Reply;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReplyDto {

    private Long replyid;
    private String content;

}
