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

    public CommentLikes(Long cmtLikesId, String cmtLikesUserName, String cmtLikesUserId, boolean cmtLikes) {
        this.cmtLikesId = cmtLikesId;
        this.cmtLikesUserName = cmtLikesUserName;
        this.cmtLikesUserId = cmtLikesUserId;
        this.cmtLikes = cmtLikes;
    }
}

