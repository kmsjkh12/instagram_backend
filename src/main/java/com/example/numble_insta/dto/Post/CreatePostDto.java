package com.example.numble_insta.dto.Post;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDto {
    private String postcontent;
    private MultipartFile postimage;
}
