package com.example.numble_insta.dto.Reply;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedReplyDto {
    private Long replyid;
    private String content;
    private String nickname;
    private String image;

}
