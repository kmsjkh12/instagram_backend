package com.example.numble_insta.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageid;
    @Column
    private String messagecontent;
    @Column
    private Date senddate;
    @ManyToOne
    @JoinColumn(name ="sendid")
    private User sendid;    //보내는 사람
    @ManyToOne
    @JoinColumn(name ="receiveid")
    private User receiveid;      //받는 사람

    @ManyToOne
    @JoinColumn(name = "chatroomid")
    private ChatRoom chatroomid;
}
