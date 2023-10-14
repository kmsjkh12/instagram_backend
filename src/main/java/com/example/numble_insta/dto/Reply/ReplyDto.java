package com.example.numble_insta.dto.Reply;

import com.example.numble_insta.entity.Comment;
import com.example.numble_insta.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    private Long replyid;
    private String replycontent;
    private Comment commentid;  //댓글 번호

    private User userid;    //댓글 작성자
}
