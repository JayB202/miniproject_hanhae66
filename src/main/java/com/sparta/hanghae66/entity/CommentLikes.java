package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity(name = "TB_CMTLIKES")
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
    private String cmtLikesUserId;

    @ColumnDefault("false")
    private boolean cmtLikes;

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
}

