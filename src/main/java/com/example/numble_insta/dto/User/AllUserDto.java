package com.example.numble_insta.dto.User;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUserDto {
    Long id;
    private String usernickname;
    private String userimage;
}
