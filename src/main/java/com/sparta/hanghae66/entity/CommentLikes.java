package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor
public class CommentLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtLikesNum;

    @Column(nullable = false)
    private Long cmtLikesId;

    @Column(nullable = false)
    private String cmtLikesUserName;

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
//    public getCommentLikes(Long commentId, String username, boolean likes) {
//        this.commentId = commentId;
//        this.username = username;
//        this.likes = likes;
//    }

    public CommentLikes(Long cmtLikesNum, Long cmtLikesId, String cmtLikesUserName, String cmtLikesUserId, boolean cmtLikes) {
        this.cmtLikesNum = cmtLikesNum;
        this.cmtLikesId = cmtLikesId;
        this.cmtLikesUserName = cmtLikesUserName;
        this.cmtLikesUserId = cmtLikesUserId;
        this.cmtLikes = cmtLikes;
    }

    public void commentLikesUpdate(boolean likes) {
        this.likes = likes;
    }
}
