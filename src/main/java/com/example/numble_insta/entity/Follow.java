package com.example.numble_insta.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "follow")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followid;


    @ManyToOne
    @JoinColumn(name ="followingid")
    private User followingid;  //작성자  다대일
    //팔로우 하는 사람

    @ManyToOne
    @JoinColumn(name ="followerid")
    private User followerid;  //작성자  다대일
    //팔로우 받는 사
}
