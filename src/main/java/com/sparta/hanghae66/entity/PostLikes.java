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
    private Long Id;

    @Column(nullable = false)
    private Long postId;          //댓글 고유 넘버

    @Column(nullable = false)
    private String username;          //좋아요 누르는 사람

    @Column(nullable = false)
    private boolean likes;          //좋아요 -> 한번더누르면 취소


    public PostLikes(Long postId, String username, boolean likes) {
        this.postId = postId;
        this.username = username;
        this.likes = likes;
    }

    public void postLikesUpdate(boolean likes) {
        this.likes = likes;
    }

}
