package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CommentLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private Long commentId;          //댓글 고유 넘버

    @Column(nullable = false)
    private String username;          //좋아요 누르는 사람

    @Column(nullable = false)
    private boolean likes;          //좋아요 -> 한번더누르면 취소

    @Column(nullable = false)
    private Long postId;            //글 고유 넘버

//    public CommentLikes(Long commentId, String userName, boolean likes, Long postId) {
//        this.commentId = commentId;
//        this.userName = userName;
//        this.likes = likes;
//        this.postId = postId;
//    }
    public CommentLikes(Long commentId, String username, boolean likes) {
        this.commentId = commentId;
        this.username = username;
        this.likes = likes;
    }

    public void commentLikesUpdate(boolean likes) {
        this.likes = likes;
    }
}
