package com.example.numble_insta.dto.Comment;

import com.example.numble_insta.dto.Reply.FeedReplyDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedCommentDto {
    private Long commentid;
    private String content;
    private String nickname;
    private String profile_image;

    List<FeedReplyDto> feedReplyDtoList;
}
