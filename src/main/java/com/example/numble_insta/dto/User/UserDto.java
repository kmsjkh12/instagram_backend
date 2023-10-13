package com.example.numble_insta.dto.User;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    private String usernickname;

    @NotNull
    private MultipartFile userimage;
}
