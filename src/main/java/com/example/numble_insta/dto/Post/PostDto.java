package com.example.numble_insta.dto.Post;

import com.example.numble_insta.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long postid;
    private String postcontent;
    private MultipartFile postimage;
}
