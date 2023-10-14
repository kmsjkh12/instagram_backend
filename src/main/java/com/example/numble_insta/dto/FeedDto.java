package com.example.numble_insta.dto;

import com.example.numble_insta.dto.Comment.FeedCommentDto;
import com.example.numble_insta.entity.Comment;
import com.example.numble_insta.entity.Post;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {
    Long postid;
    String content;
    String image;

    List<FeedCommentDto> comment;


}
