package com.example.numble_insta.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "chatroom")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomid;

    @ManyToOne
    @JoinColumn(name ="sendid")
    private User sendid;      //받는 사람

    @ManyToOne
    @JoinColumn(name ="receiveid")
    private User receiveid;      //받는 사람



}
