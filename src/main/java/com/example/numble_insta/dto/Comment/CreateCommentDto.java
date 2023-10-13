package com.example.numble_insta.dto.Comment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDto {

    String content;
    Long postid;

}
