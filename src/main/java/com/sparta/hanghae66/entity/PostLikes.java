package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikesNum;

    @Column(nullable = false)
    private Long postLikesId;

    @Column(nullable = false)
    private String postLikesUserName;

    @Column(nullable = false)
    private boolean likes;          //좋아요 -> 한번더누르면 취소


    public PostLikes(Long postLikesNum, Long postLikesId, String postLikesUserName, String postLikesUserId, boolean postLikes) {
        this.postLikesNum = postLikesNum;
        this.postLikesId = postLikesId;
        this.postLikesUserName = postLikesUserName;
        this.postLikesUserId = postLikesUserId;
        this.postLikes = postLikes;
    }

}
