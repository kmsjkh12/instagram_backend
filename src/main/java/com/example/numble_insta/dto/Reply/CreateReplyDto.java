package com.example.numble_insta.dto.Reply;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReplyDto {
    Long comment_id;
    String content;
}
