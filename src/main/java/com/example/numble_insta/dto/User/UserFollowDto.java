package com.example.numble_insta.dto.User;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowDto {
    private String nickname;
    private String profile_image;
    private long follower;
    private long following;
}
