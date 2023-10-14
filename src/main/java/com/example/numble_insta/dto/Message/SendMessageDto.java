package com.example.numble_insta.dto.Message;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageDto {

    Long send_id;
    String content;
}
